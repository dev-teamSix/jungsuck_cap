import requests
from bs4 import BeautifulSoup
import time

# Base URL
base_url = "https://whoisnerdy.com"
# Base Page URL
base_page_url = "https://whoisnerdy.com/board/free/list.html"
# First page of the board
board_url = "https://whoisnerdy.com/board/free/list.html?board_no=3&page=1"

# Function to crawl a single page and extract titles and bodies
def crawl_page(url):
    response = requests.get(url, headers={"User-Agent": "Mozilla/5.0"})
    soup = BeautifulSoup(response.text, "html.parser")
    
    # Placeholder to store the data
    page_data = []

    # Find all the links (title) on the page
    links = soup.find_all('a', href=True)

    for link in links:
        href = link['href']
        # Check if it's the type of link we're interested in (title)
        if "/board/free/read.html?" in href:
            full_link = base_url + href
            title = link.text.strip()
            print(f"Found title: {title} at {full_link}")

            # Visit the link (body)
            linked_page = requests.get(full_link, headers={"User-Agent": "Mozilla/5.0"})
            linked_soup = BeautifulSoup(linked_page.text, "html.parser")
            
            # Extract body value from the linked page using the specific div
            body_div = linked_soup.find("div", class_="fr-view fr-view-article")
            body = body_div.get_text(strip=True) if body_div else "No body content"

            # Store title and body
            page_data.append({
                "title": title,
                "body": body
            })

            # Optional delay to prevent overwhelming the server
            time.sleep(1)
    
    return page_data, soup

# Function to find next page link
def find_next_page(soup, current_url):
    pageStr = current_url[60:]
    page = int(pageStr) + 1
    next_page_link = soup.find('a', class_='other', text=str(page))
    if next_page_link:
        next_page_href = next_page_link['href']
        next_page_url = base_page_url + next_page_href
        return next_page_url
    return None

# Start crawling from the first page
current_url = board_url
all_data = []

while current_url:
    print(f"Crawling page: {current_url}")
    page_data, soup = crawl_page(current_url)
    all_data.extend(page_data)
    
    # Find the link to the next page
    current_url = find_next_page(soup, current_url)


import os
import json

dataset = {
    "system": "You are a helpful shopping mall assistant",
    "train": []
}

# Output the collected data
for entry in all_data:
    print(f"Title: {entry['title']}\nBody: {entry['body']}\n")
    new_train = {"instruction":entry['title'], "output":entry['body']}
    dataset["train"].append(new_train)


import pymysql

db_passwd = os.getenv('DB_PASSWD')
db = pymysql.connect(host='localhost',
                     port=3306,
                     user='root',
                     passwd=db_passwd,
                     db='cap_jungsuck',
                     charset='utf8mb4')

cursor = db.cursor()

list_message = []
num_data = len(dataset["train"])

for i in range(num_data):
    instruction = dataset["train"][i]["instruction"]
    output = dataset["train"][i]["output"]
    message = [
        {"role": "system", "content": dataset["system"]},
        {"role": "user", "content": instruction},
        {"role": "assistant", "content": output},
    ]
    list_message.append(message)
    query = f'INSERT INTO qna_data (question, response) VALUES ("{instruction}", "{output}");'
    cursor.execute(query)
    db.commit()

db.close()