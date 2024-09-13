from flask import Flask, request, render_template
from dotenv import load_dotenv
import os
from openai import OpenAI
import pymysql
import json

load_dotenv()

app = Flask(__name__)

api_key = os.getenv('OPEN_API_KEY')
client = OpenAI(api_key=api_key)
db_passwd = os.getenv('DB_PASSWD')
db = pymysql.connect(host='localhost',
                     port=3306,
                     user='root',
                     passwd=db_passwd,
                     db='cap_jungsuck',
                     charset='utf8mb4')

cursor = db.cursor()

def make_prompt(user_input):
    res = client.chat.completions.create(
        model='gpt-4o-mini',
        messages=user_input
    )
    return res.choices[0].message.content

@app.route('/receive_string', methods=["POST"])
def receive_string():
    dto_json = request.get_json()
    user_input = json.dumps(dto_json["user_input"], ensure_ascii=False)
    cust_id = json.dumps(dto_json["cust_id"], ensure_ascii=False)
    
    if '구매내역' in user_input:
        if 'guest' in cust_id:
            bot_response = '로그인 해주세요.'
        else:
            # id = extract_customer_id(user_input)
            id = cust_id[1:-1]
            query = f"SELECT * FROM cust WHERE id='{id}'"
            
            cursor.execute(query)
            cust = cursor.fetchone()

            if cust:
                query = f"SELECT * FROM orders WHERE cust_id='{id}' ORDER BY ord_no DESC;"
                cursor.execute(query)
                orders = cursor.fetchall()

                if orders:
                    order_tmp_list = []
                    order_tmp_list.append("주문 내역:")
                    cnt = 1;
                    for o in orders:
                        if o[2] == 'O':
                            orderStatus = "주문 완료"
                        else:
                            orderStatus = "주문 취소"
                            
                        order_tmp_list.append(f"{cnt}번:\n {orderStatus}, {o[3]}, {o[4]}")
                        
                        query = f"SELECT * FROM order_item WHERE ord_no={o[0]}"
                        cursor.execute(query)
                        order_items = cursor.fetchall()
                        for oi in order_items:
                            order_tmp_list.append(f"{oi[4]}개, {oi[5]}, {oi[6]}원")
                        cnt = cnt + 1;
                        
                    order_detail = "\n".join(order_tmp_list)

                    bot_response = f"{cust[4]}({cust[0]})님의 주문 내역은 다음과 같습니다.\n{order_detail}"
                else:
                    bot_response = '주문 내역이 없습니다.'
            else:
                bot_response = '유저가 확인되지 않습니다. 다시 시도해주세요.'
    else:
        conversation = [{"role":"system", "content":"You are a very kindful and helpful shopping mall C/S assistant"}]
        query = f"SELECT * FROM qna_data"
        cursor.execute(query)
        list_message = cursor.fetchall()
        for lm in list_message:
            conversation.extend([{"role": "user", "content":lm[1]}])
            conversation.extend([{"role": "assistant", "content":lm[2]}])
        query = f"SELECT * FROM msg_data"
        cursor.execute(query)
        msg_history = cursor.fetchall()
        for msg in msg_history:
            conversation.extend([{"role": "user", "content":msg[2]}])
            conversation.extend([{"role": "assistant", "content":msg[3]}])
        conversation.append({"role":"user", "content":user_input})
        bot_response = make_prompt(conversation)

    query1 = f"INSERT INTO msg_data(cust_id, role_data, content_data) VALUES({cust_id}, 'user', {user_input})"
    cursor.execute(query1)
    db.commit()
    query2 = f"INSERT INTO msg_data(cust_id, role_data, content_data) VALUES({cust_id}, 'assistant', '{bot_response}')"
    cursor.execute(query2)
    db.commit()

    return bot_response


import re
def extract_customer_id(input_text):
    id_pattern = r"아이디\s*:\s*([a-zA-Z0-9._%+-]*)"

    id_match = re.search(id_pattern, input_text)

    id = id_match.group(1) if id_match else None

    print('유저정보', id)

    return id


if __name__ == "__main__":
    app.run('0.0.0.0', port=8082, debug=True)