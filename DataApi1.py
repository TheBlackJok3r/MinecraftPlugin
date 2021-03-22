import json
import os
import asyncio
from googleapiclient.discovery import build
import threading
import time


#opening data
with open('file dir')as jfile:
    data=json.load(jfile)
    temp=data
    jfile.close()


#saving Data
def write_json(data, filename='file dir'):
    with open(filename, 'w') as f:
        json.dump(data, f, indent=5)
        jfile.close()


def rating():
    youtube = build('youtube', 'v3', developerKey='developer key')

    request = youtube.videos().list(
        part="statistics",
        id="Yt video Id"
    )
    response=request.execute()
    write_json(response)
    print(response)


while True:
    rating()
    time.sleep(5)