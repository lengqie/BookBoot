# @coding: UTF-8
import pymysql
import random

db = pymysql.connect("127.0.0.1", "root", "", "boot_book")
#
# s = """""".replace("，","、")
# l = [i.strip()[1:-1] for i in s.split("、")]

cursor = db.cursor()


def fakeName():

   first = ["","古","今","中","外","近","现","国家","国际","军事","今日"]

   second = chr(random.randint(0x4e00, 0x6e00))
   third = chr(random.randint(0x6e00, 0x9fbf))
   end = ["纪","志", "选", "集", "摘","经","决","目录","报纸", "新闻", "全集","演艺","故事", "寓言", "神话", "排行榜"]

   return random.choice(first) + second + third + random.choice(end)
# i =10
for i in range(10,1000):
	for j in range(10,1000):
		for k in range(106,126):
			sql = "insert into book(isbn,name,type,create_time,update_time) values(%s,%s,'Fake','2021-04-29 14:19:43','2021-04-29 14:19:43')"

			cursor.execute(sql,("202-{}-{}-{}".format(str(k),str(j),str(i)),fakeName() ))

			db.commit()
			i+=1

# print(fakeName())

