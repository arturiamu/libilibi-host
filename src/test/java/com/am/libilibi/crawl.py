import re
from pyquery import PyQuery as pq
import requests
from fake_useragent import UserAgent
import pymysql

ua = UserAgent()  # ua.random
# db = pymysql.connect(host="175.24.175.35", user='root', password='20000102@Mlb', database='libilibi')

proxies = []


def save_proxy():
#     cursor = db.cursor()
#     sql = 'truncate table lbproxy'
#     cursor.execute(sql)
#     db.commit()
    print("saving...")
    for i in proxies:
        print(i)
#         host = "'" + i[0] + "'"
#         port = "'" + i[1] + "'"
#         sql = 'INSERT INTO lbproxy(host,port) VALUES({},{})'.format(host, port)
#         print(sql)
#         cursor.execute(sql)
#     db.commit()
#     db.close()


def get_page(url):
    """
    抓取代理
    :param url:
    :param options:
    :return:
    """
    headers = {'User-Agent': ua.random}
    print('正在抓取', url)
    try:
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            print('抓取成功', url, response.status_code)
            return response.text
    except ConnectionError:
        print('抓取失败', url)
        return None


def crawl_xicidaili():
    for i in range(1, 3):
        start_url = 'http://www.xicidaili.com/nn/{}'.format(i)
        html = get_page(start_url)
        if html:
            find_trs = re.compile('<tr class.*?>(.*?)</tr>', re.S)
            trs = find_trs.findall(html)
            for tr in trs:
                find_ip = re.compile('<td>(\d+\.\d+\.\d+\.\d+)</td>')
                re_ip_address = find_ip.findall(tr)
                find_port = re.compile('<td>(\d+)</td>')
                re_port = find_port.findall(tr)
                for address, port in zip(re_ip_address, re_port):
                    address_port = address + ':' + port
                    print(address_port.replace(' ', ''))


def crawl_kuaidaili():
    for i in range(1, 4):
        start_url = 'http://www.kuaidaili.com/free/inha/{}/'.format(i)
        html = get_page(start_url)
        if html:
            ip_address = re.compile('<td data-title="IP">(.*?)</td>')
            re_ip_address = ip_address.findall(html)
            port = re.compile('<td data-title="PORT">(.*?)</td>')
            re_port = port.findall(html)
            for address, port in zip(re_ip_address, re_port):
                address_port = address + ':' + port
                print(address_port.replace(' ', ''))


def crawl_daili66(cnt=4):
    """
    获取代理66
    :param cnt: 页码
    :return: 代理
    """
    start_url = 'http://www.66ip.cn/{}.html'
    urls = [start_url.format(page) for page in range(1, cnt + 1)]
    for url in urls:
        print('Crawling', url)
        html = get_page(url)
        if html:
            doc = pq(html)
            trs = doc('.containerbox table tr:gt(0)').items()
            for tr in trs:
                ip = tr.find('td:nth-child(1)').text()
                port = tr.find('td:nth-child(2)').text()
                proxies.append((ip, port))


def crawl_ip3366(cnt=9):
    for i in range(1, cnt):
        start_url = 'http://www.ip3366.net/?stype=1&page={}'.format(i)
        html = get_page(start_url)
        if html:
            find_tr = re.compile('<tr>(.*?)</tr>', re.S)
            trs = find_tr.findall(html)
            for s in range(1, len(trs)):
                find_ip = re.compile('<td>(\d+\.\d+\.\d+\.\d+)</td>')
                re_ip_address = find_ip.findall(trs[s])
                find_port = re.compile('<td>(\d+)</td>')
                re_port = find_port.findall(trs[s])
                for address, port in zip(re_ip_address, re_port):
                    proxies.append((address, port))


def start():
    # crawl_kuaidaili()  # ssl
    # crawl_xicidaili()  # 失效
    crawl_daili66()  # 2698
    crawl_ip3366()  # 10页


if __name__ == '__main__':
    proxies = []
    start()
    proxies = list(set(proxies))
    save_proxy()
