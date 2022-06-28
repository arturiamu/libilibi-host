# adastra 视频网站


**简介**:adastra 视频网站


**HOST**:http://adastra.isamumu.cn


**联系人**:adastra 开发组


**Version**:1.0.0


**接口路径**:/v2/api-docs?group=1.0.0


[TOC]






# 个性化推荐模块


## 获取个性化推荐


**接口地址**:`/interest/{ps}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ps|ps|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Video»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|Video|
|&emsp;&emsp;aid|视频id|integer(int64)||
|&emsp;&emsp;coin|投币数|integer(int32)||
|&emsp;&emsp;danmaku|视频弹幕数|integer(int32)||
|&emsp;&emsp;desc|视频描述|string||
|&emsp;&emsp;favorite|收藏数|integer(int32)||
|&emsp;&emsp;like|点赞数|integer(int32)||
|&emsp;&emsp;pic|视频封面图片链接|string||
|&emsp;&emsp;pid|视频分类id|integer(int32)||
|&emsp;&emsp;reply|视频评论数|integer(int32)||
|&emsp;&emsp;share|分享数|integer(int32)||
|&emsp;&emsp;tid|视频类型id|integer(int32)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;tname|视频分类名|string||
|&emsp;&emsp;view|视频播放量|integer(int32)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"aid": 3581515,
			"coin": 25,
			"danmaku": 2761,
			"desc": "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...",
			"favorite": 78,
			"like": 3564,
			"pic": "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png",
			"pid": 11,
			"reply": 1,
			"share": 5,
			"tid": 185,
			"title": "【国产】亲爱的【28集全】",
			"tname": "国产剧",
			"view": 40107
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


# 历史记录模块


## 获取用户历史记录


**接口地址**:`/history/get/{ps}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ps|ps|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«UserHistorySimpleVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|UserHistorySimpleVO|
|&emsp;&emsp;createTime|浏览时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int32)||
|&emsp;&emsp;uid|用户id|integer(int64)||
|&emsp;&emsp;video|视频|Video|Video|
|&emsp;&emsp;&emsp;&emsp;aid|视频id|integer||
|&emsp;&emsp;&emsp;&emsp;coin|投币数|integer||
|&emsp;&emsp;&emsp;&emsp;danmaku|视频弹幕数|integer||
|&emsp;&emsp;&emsp;&emsp;desc|视频描述|string||
|&emsp;&emsp;&emsp;&emsp;favorite|收藏数|integer||
|&emsp;&emsp;&emsp;&emsp;like|点赞数|integer||
|&emsp;&emsp;&emsp;&emsp;pic|视频封面图片链接|string||
|&emsp;&emsp;&emsp;&emsp;pid|视频分类id|integer||
|&emsp;&emsp;&emsp;&emsp;reply|视频评论数|integer||
|&emsp;&emsp;&emsp;&emsp;share|分享数|integer||
|&emsp;&emsp;&emsp;&emsp;tid|视频类型id|integer||
|&emsp;&emsp;&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;&emsp;&emsp;tname|视频分类名|string||
|&emsp;&emsp;&emsp;&emsp;view|视频播放量|integer||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"createTime": "2022-6-28 13:44:26",
			"id": 1,
			"uid": 1,
			"video": {
				"aid": 3581515,
				"coin": 25,
				"danmaku": 2761,
				"desc": "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...",
				"favorite": 78,
				"like": 3564,
				"pic": "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png",
				"pid": 11,
				"reply": 1,
				"share": 5,
				"tid": 185,
				"title": "【国产】亲爱的【28集全】",
				"tname": "国产剧",
				"view": 40107
			}
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 删除历史记录


**接口地址**:`/history/del/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 清空历史记录


**接口地址**:`/history/del/all`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 添加历史记录


**接口地址**:`/history/add`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "aid": 3581515,
  "categoryName": "收藏夹1",
  "createTime": "2022-6-28 13:37:17",
  "id": 1,
  "pid": 11,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoOperateDTO|videoOperateDTO|body|true|VideoOperateDTO|VideoOperateDTO|
|&emsp;&emsp;aid|视频id||false|integer(int32)||
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;createTime|收藏时间||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int32)||
|&emsp;&emsp;pid|视频分类id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 头像模块


## 更新用户头像


**接口地址**:`/avatar/updateAvatar`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|url|url|body|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 添加用户头像


**接口地址**:`/avatar/addAvatar`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|url|url|body|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Video»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||Video|Video|
|&emsp;&emsp;aid|视频id|integer(int64)||
|&emsp;&emsp;coin|投币数|integer(int32)||
|&emsp;&emsp;danmaku|视频弹幕数|integer(int32)||
|&emsp;&emsp;desc|视频描述|string||
|&emsp;&emsp;favorite|收藏数|integer(int32)||
|&emsp;&emsp;like|点赞数|integer(int32)||
|&emsp;&emsp;pic|视频封面图片链接|string||
|&emsp;&emsp;pid|视频分类id|integer(int32)||
|&emsp;&emsp;reply|视频评论数|integer(int32)||
|&emsp;&emsp;share|分享数|integer(int32)||
|&emsp;&emsp;tid|视频类型id|integer(int32)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;tname|视频分类名|string||
|&emsp;&emsp;view|视频播放量|integer(int32)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"aid": 3581515,
		"coin": 25,
		"danmaku": 2761,
		"desc": "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...",
		"favorite": 78,
		"like": 3564,
		"pic": "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png",
		"pid": 11,
		"reply": 1,
		"share": 5,
		"tid": 185,
		"title": "【国产】亲爱的【28集全】",
		"tname": "国产剧",
		"view": 40107
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 持久化用户头像到oss


**接口地址**:`/avatar/ossfile`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|file|formData|false|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«string»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": "",
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取用户头像


**接口地址**:`/avatar/getAvatar`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«string»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": "",
	"message": "",
	"state": 0,
	"success": true
}
```


# 支付模块


## 支付回调


**接口地址**:`/pay/callback`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 查询支付状态


**接口地址**:`/pay/query`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|outTradeNo|outTradeNo|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


## 支付


**接口地址**:`/pay/topay`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|money|money|query|false|number||
|subject|subject|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


暂无


**响应示例**:
```javascript

```


# 收藏夹模块


## 获取收藏夹


**接口地址**:`/category/selectByCategory`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«UserCategorySimpleVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|UserCategorySimpleVO|
|&emsp;&emsp;categoryName|收藏夹名字|string||
|&emsp;&emsp;remarks|收藏夹备注|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"categoryName": "收藏夹1",
			"remarks": "学习资料"
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 清空收藏夹


**接口地址**:`/category/clear/{categoryName}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryName|categoryName|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 删除收藏夹


**接口地址**:`/category/del/{categoryName}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryName|categoryName|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 新建收藏夹


**接口地址**:`/category/add`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "categoryName": "收藏夹1",
  "id": 1,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userCategory|userCategory|body|true|UserCategoryAddDTO|UserCategoryAddDTO|
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;id|收藏夹id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 收藏模块


## 取消收藏


**接口地址**:`/collection/cancel/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取收藏夹下的视频


**接口地址**:`/collection/selectByCategory/{category}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|category|category|path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«UserCollectionSimpleVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|UserCollectionSimpleVO|
|&emsp;&emsp;categoryName|收藏夹名字|string||
|&emsp;&emsp;createTime|收藏时间|string(date-time)||
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;uid|用户id|integer(int64)||
|&emsp;&emsp;video|视频|array|Video|
|&emsp;&emsp;&emsp;&emsp;aid|视频id|integer||
|&emsp;&emsp;&emsp;&emsp;coin|投币数|integer||
|&emsp;&emsp;&emsp;&emsp;danmaku|视频弹幕数|integer||
|&emsp;&emsp;&emsp;&emsp;desc|视频描述|string||
|&emsp;&emsp;&emsp;&emsp;favorite|收藏数|integer||
|&emsp;&emsp;&emsp;&emsp;like|点赞数|integer||
|&emsp;&emsp;&emsp;&emsp;pic|视频封面图片链接|string||
|&emsp;&emsp;&emsp;&emsp;pid|视频分类id|integer||
|&emsp;&emsp;&emsp;&emsp;reply|视频评论数|integer||
|&emsp;&emsp;&emsp;&emsp;share|分享数|integer||
|&emsp;&emsp;&emsp;&emsp;tid|视频类型id|integer||
|&emsp;&emsp;&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;&emsp;&emsp;tname|视频分类名|string||
|&emsp;&emsp;&emsp;&emsp;view|视频播放量|integer||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"categoryName": "收藏夹1",
			"createTime": "2022-6-28 13:43:19",
			"id": 1,
			"uid": 1,
			"video": "video"
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 查询用户所有收藏记录


**接口地址**:`/collection/selectCategory`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Map«string,object»»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|Map«string,object»|
|&emsp;&emsp;additionalProperty1||Map«string,object»|Map«string,object»|
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 添加收藏


**接口地址**:`/collection/add`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "aid": 3581515,
  "categoryName": "收藏夹1",
  "createTime": "2022-6-28 13:37:17",
  "id": 1,
  "pid": 11,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoOperateDTO|videoOperateDTO|body|true|VideoOperateDTO|VideoOperateDTO|
|&emsp;&emsp;aid|视频id||false|integer(int32)||
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;createTime|收藏时间||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int32)||
|&emsp;&emsp;pid|视频分类id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 数据模块


## 获取21个视频大分类的观看次数


**接口地址**:`/echarts/videoHeat`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Map«string,object»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||object||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {},
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取每个月新增的人数


**接口地址**:`/echarts/members`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Map«string,object»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||object||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {},
	"message": "",
	"state": 0,
	"success": true
}
```


# 消息模块


## 删除一条消息


**接口地址**:`/message/del/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 删除全部消息


**接口地址**:`/message/delAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 标记已读一条消息


**接口地址**:`/message/read/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|id|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 已读全部消息


**接口地址**:`/message/readAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 发送消息


**接口地址**:`/message/send`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "date": "2022-6-28 13:31:15",
  "del": 1,
  "id": 1,
  "isAdmin": true,
  "read": 1,
  "sendUserId": 1,
  "sendUserName": "arturiamu",
  "targetUserId": 1,
  "text": "你好"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|messageDTO|messageDTO|body|true|MessageDTO|MessageDTO|
|&emsp;&emsp;date|发送时间||false|string(date-time)||
|&emsp;&emsp;del|消息是否删除||false|integer(int32)||
|&emsp;&emsp;id|消息id||false|integer(int64)||
|&emsp;&emsp;isAdmin|是否管理员发送||false|boolean||
|&emsp;&emsp;read|消息是否已读||false|integer(int32)||
|&emsp;&emsp;sendUserId|发送方id||false|integer(int64)||
|&emsp;&emsp;sendUserName|发送方用户名||false|string||
|&emsp;&emsp;targetUserId|接收方id||false|integer(int64)||
|&emsp;&emsp;text|消息内容||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取用户所有消息


**接口地址**:`/message/getAll`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«MessageDTO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|MessageDTO|
|&emsp;&emsp;date|发送时间|string(date-time)||
|&emsp;&emsp;del|消息是否删除|integer(int32)||
|&emsp;&emsp;id|消息id|integer(int64)||
|&emsp;&emsp;isAdmin|是否管理员发送|boolean||
|&emsp;&emsp;read|消息是否已读|integer(int32)||
|&emsp;&emsp;sendUserId|发送方id|integer(int64)||
|&emsp;&emsp;sendUserName|发送方用户名|string||
|&emsp;&emsp;targetUserId|接收方id|integer(int64)||
|&emsp;&emsp;text|消息内容|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"date": "2022-6-28 13:31:15",
			"del": 1,
			"id": 1,
			"isAdmin": true,
			"read": 1,
			"sendUserId": 1,
			"sendUserName": "arturiamu",
			"targetUserId": 1,
			"text": "你好"
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


# 点赞模块


## 取消点赞记录


**接口地址**:`/like/cancel`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "aid": 3581515,
  "categoryName": "收藏夹1",
  "createTime": "2022-6-28 13:37:17",
  "id": 1,
  "pid": 11,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoOperateDTO|videoOperateDTO|body|true|VideoOperateDTO|VideoOperateDTO|
|&emsp;&emsp;aid|视频id||false|integer(int32)||
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;createTime|收藏时间||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int32)||
|&emsp;&emsp;pid|视频分类id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 判断是否点赞视频


**接口地址**:`/like/isLike`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "aid": 3581515,
  "categoryName": "收藏夹1",
  "createTime": "2022-6-28 13:37:17",
  "id": 1,
  "pid": 11,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoOperateDTO|videoOperateDTO|body|true|VideoOperateDTO|VideoOperateDTO|
|&emsp;&emsp;aid|视频id||false|integer(int32)||
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;createTime|收藏时间||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int32)||
|&emsp;&emsp;pid|视频分类id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 添加点赞记录


**接口地址**:`/like/add`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "aid": 3581515,
  "categoryName": "收藏夹1",
  "createTime": "2022-6-28 13:37:17",
  "id": 1,
  "pid": 11,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|videoOperateDTO|videoOperateDTO|body|true|VideoOperateDTO|VideoOperateDTO|
|&emsp;&emsp;aid|视频id||false|integer(int32)||
|&emsp;&emsp;categoryName|收藏夹名字||false|string||
|&emsp;&emsp;createTime|收藏时间||false|string(date-time)||
|&emsp;&emsp;id|id||false|integer(int32)||
|&emsp;&emsp;pid|视频分类id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 用户建议模块


## 获取用户的建议


**接口地址**:`/advise/select`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«AdviseDTO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|AdviseDTO|
|&emsp;&emsp;advise|用户建议内容|string||
|&emsp;&emsp;adviseTime|时间|string(date-time)||
|&emsp;&emsp;id|用户建议id|integer(int32)||
|&emsp;&emsp;uid|用户id|integer(int64)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"advise": "颜色太突兀",
			"adviseTime": "2022-6-28 13:28:20",
			"id": 1,
			"uid": 1
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 用户反馈建议


**接口地址**:`/advise/add`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "advise": "颜色太突兀",
  "adviseTime": "2022-6-28 13:28:20",
  "id": 1,
  "uid": 1
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|advise|advise|body|true|AdviseDTO|AdviseDTO|
|&emsp;&emsp;advise|用户建议内容||false|string||
|&emsp;&emsp;adviseTime|时间||false|string(date-time)||
|&emsp;&emsp;id|用户建议id||false|integer(int32)||
|&emsp;&emsp;uid|用户id||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 用户模块


## 判断验证码


**接口地址**:`/user/verify`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|code|code|body|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 用户注册


**接口地址**:`/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求示例**:


```javascript
{
  "user": {
    "account": 15911245016,
    "id": 1,
    "items": "items",
    "password": 123456789,
    "state": "normal",
    "username": "arturiamu"
  },
  "verCode": 123456
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|rp|rp|body|true|UserRegisterDTO|UserRegisterDTO|
|&emsp;&emsp;user|用户||false|User|User|
|&emsp;&emsp;&emsp;&emsp;account|用户账号||false|string||
|&emsp;&emsp;&emsp;&emsp;id|用户id||false|integer||
|&emsp;&emsp;&emsp;&emsp;items|用户兴趣分区||false|array|Item|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id|id||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;name|分类名字||false|string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;pid|父分类id||false|integer||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;uri|分类uri||false|string||
|&emsp;&emsp;&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;&emsp;&emsp;state|用户状态||false|string||
|&emsp;&emsp;&emsp;&emsp;username|用户名||false|string||
|&emsp;&emsp;verCode|验证码||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 用户登录


**接口地址**:`/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求示例**:


```javascript
{
  "account": 15911245016,
  "id": 1,
  "items": "items",
  "password": 123456789,
  "state": "normal",
  "username": "arturiamu"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|user|body|true|User|User|
|&emsp;&emsp;account|用户账号||false|string||
|&emsp;&emsp;id|用户id||false|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区||false|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id||false|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字||false|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id||false|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;state|用户状态||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 判断用户是否登录


**接口地址**:`/user/isLogin`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 修改用户密码


**接口地址**:`/user/updatePwd`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求示例**:


```javascript
{
  "account": 15911245016,
  "password": 123456789,
  "verCode": 123456
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|up|up|body|true|UpdatePwdDTO|UpdatePwdDTO|
|&emsp;&emsp;account|账号||false|string||
|&emsp;&emsp;password|新密码||false|string||
|&emsp;&emsp;verCode|验证码||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 更新用户信息


**接口地址**:`/user/update`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求示例**:


```javascript
{
  "account": 15911245016,
  "id": 1,
  "items": "items",
  "password": 123456789,
  "state": "normal",
  "username": "arturiamu"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|user|user|body|true|User|User|
|&emsp;&emsp;account|用户账号||false|string||
|&emsp;&emsp;id|用户id||false|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区||false|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id||false|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字||false|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id||false|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;state|用户状态||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 退出登录


**接口地址**:`/user/logout`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 发送验证码


**接口地址**:`/user/verifyCode`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`application/json;charset=utf-8`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|account|account|body|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


# 管理员操作用户


## 更新用户信息


**接口地址**:`/admin/updateUser`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "account": 15911245016,
  "id": 1,
  "jsItems": "items",
  "password": 123456789,
  "state": "normal",
  "username": "arturiamu"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userDBO|userDBO|body|true|UserDBO|UserDBO|
|&emsp;&emsp;account|用户账号||false|string||
|&emsp;&emsp;id|用户id||false|integer(int64)||
|&emsp;&emsp;jsItems|用户兴趣分区||false|string||
|&emsp;&emsp;password|用户密码||false|string||
|&emsp;&emsp;state|用户状态||false|string||
|&emsp;&emsp;username|用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«User»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||User|User|
|&emsp;&emsp;account|用户账号|string||
|&emsp;&emsp;id|用户id|integer(int64)||
|&emsp;&emsp;items|用户兴趣分区|array|Item|
|&emsp;&emsp;&emsp;&emsp;id|id|integer||
|&emsp;&emsp;&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;&emsp;&emsp;pid|父分类id|integer||
|&emsp;&emsp;&emsp;&emsp;uri|分类uri|string||
|&emsp;&emsp;password|用户密码|string||
|&emsp;&emsp;state|用户状态|string||
|&emsp;&emsp;username|用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"account": 15911245016,
		"id": 1,
		"items": "items",
		"password": 123456789,
		"state": "normal",
		"username": "arturiamu"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 修改用户状态


**接口地址**:`/admin/changeState/{uid}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|uid|uid|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取所有用户信息


**接口地址**:`/admin/export`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取所有用户的登录日志


**接口地址**:`/admin/allUserLog`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«UserLoginLogVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|UserLoginLogVO|
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;ip|登录ip|string||
|&emsp;&emsp;time|登录时间|string(date-time)||
|&emsp;&emsp;uid|用户id|integer(int64)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"id": 1,
			"ip": "127.0.0.1",
			"time": "2022-6-28 13:45:32",
			"uid": 1
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取指定uid用户的登录日志


**接口地址**:`/admin/UserLog/{uid}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|uid|uid|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«UserLoginLogVO»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|UserLoginLogVO|
|&emsp;&emsp;id|id|integer(int64)||
|&emsp;&emsp;ip|登录ip|string||
|&emsp;&emsp;time|登录时间|string(date-time)||
|&emsp;&emsp;uid|用户id|integer(int64)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"id": 1,
			"ip": "127.0.0.1",
			"time": "2022-6-28 13:45:32",
			"uid": 1
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 选择用户


**接口地址**:`/admin/selectUser/{cur}/{pageSize}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cur|cur|path|true|integer(int32)||
|pageSize|pageSize|path|true|integer(int32)||
|username|username|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Map«string,object»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||object||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {},
	"message": "",
	"state": 0,
	"success": true
}
```


# 管理员模块


## 判断管理员是否登录


**接口地址**:`/admin/isLogin`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Admin»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||Admin|Admin|
|&emsp;&emsp;avatar|管理员头像|string||
|&emsp;&emsp;id|管理员id|integer(int64)||
|&emsp;&emsp;nickname|管理员昵称|string||
|&emsp;&emsp;password|管理员密码|string||
|&emsp;&emsp;username|管理员用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"avatar": "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png",
		"id": 1,
		"nickname": "阿木木",
		"password": 123456,
		"username": "amm"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


## 退出登录


**接口地址**:`/admin/logout`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 更新管理员信息


**接口地址**:`/admin/updateAdmin`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "avatar": "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png",
  "id": 1,
  "nickname": "阿木木",
  "username": "admin"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|adminDTO|adminDTO|body|true|AdminDTO|AdminDTO|
|&emsp;&emsp;avatar|管理员头像||false|string||
|&emsp;&emsp;id|管理员id||false|integer(int64)||
|&emsp;&emsp;nickname|管理员昵称||false|string||
|&emsp;&emsp;username|管理员用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«Void»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"message": "",
	"state": 0,
	"success": true
}
```


## 管理员登录


**接口地址**:`/admin/login`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "avatar": "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png",
  "id": 1,
  "nickname": "阿木木",
  "password": 123456,
  "username": "amm"
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|admin|admin|body|true|Admin|Admin|
|&emsp;&emsp;avatar|管理员头像||false|string||
|&emsp;&emsp;id|管理员id||false|integer(int64)||
|&emsp;&emsp;nickname|管理员昵称||false|string||
|&emsp;&emsp;password|管理员密码||false|string||
|&emsp;&emsp;username|管理员用户名||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«AdminVO»|
|201|Created||
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||AdminVO|AdminVO|
|&emsp;&emsp;avatar|管理员头像|string||
|&emsp;&emsp;id|管理员id|integer(int64)||
|&emsp;&emsp;nickname|管理员昵称|string||
|&emsp;&emsp;password|管理员密码|string||
|&emsp;&emsp;username|管理员用户名|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": {
		"avatar": "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png",
		"id": 1,
		"nickname": "阿木木",
		"password": 123456,
		"username": "admin"
	},
	"message": "",
	"state": 0,
	"success": true
}
```


# 视频分类模块


## 获取默认分类


**接口地址**:`/item/default`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Item»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|Item|
|&emsp;&emsp;id|id|integer(int32)||
|&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;pid|父分类id|integer(int32)||
|&emsp;&emsp;uri|分类uri|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"id": 1,
			"name": "舞蹈",
			"pid": 11,
			"uri": "dance"
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取所有分类


**接口地址**:`/item/all`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Item»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|Item|
|&emsp;&emsp;id|id|integer(int32)||
|&emsp;&emsp;name|分类名字|string||
|&emsp;&emsp;pid|父分类id|integer(int32)||
|&emsp;&emsp;uri|分类uri|string||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"id": 1,
			"name": "舞蹈",
			"pid": 11,
			"uri": "dance"
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```


# 视频模块


## 获取搜索视频


**接口地址**:`/video/search/{keyword}/{offset}/{ps}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|keyword|keyword|path|true|string||
|offset|offset|path|true|integer(int32)||
|ps|ps|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Video|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|aid|视频id|integer(int64)|integer(int64)|
|coin|投币数|integer(int32)|integer(int32)|
|danmaku|视频弹幕数|integer(int32)|integer(int32)|
|desc|视频描述|string||
|favorite|收藏数|integer(int32)|integer(int32)|
|like|点赞数|integer(int32)|integer(int32)|
|pic|视频封面图片链接|string||
|pid|视频分类id|integer(int32)|integer(int32)|
|reply|视频评论数|integer(int32)|integer(int32)|
|share|分享数|integer(int32)|integer(int32)|
|tid|视频类型id|integer(int32)|integer(int32)|
|title|视频标题|string||
|tname|视频分类名|string||
|view|视频播放量|integer(int32)|integer(int32)|


**响应示例**:
```javascript
[
	{
		"aid": 3581515,
		"coin": 25,
		"danmaku": 2761,
		"desc": "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...",
		"favorite": 78,
		"like": 3564,
		"pic": "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png",
		"pid": 11,
		"reply": 1,
		"share": 5,
		"tid": 185,
		"title": "【国产】亲爱的【28集全】",
		"tname": "国产剧",
		"view": 40107
	}
]
```


## 获取直播视频


**接口地址**:`/video/live/{ps}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|ps|ps|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«string»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [],
	"message": "",
	"state": 0,
	"success": true
}
```


## 获取直播间数量


**接口地址**:`/video/live/size`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«int»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||integer(int32)|integer(int32)|
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": 0,
	"message": "",
	"state": 0,
	"success": true
}
```


## 按照分类获取视频


**接口地址**:`/video/pid/{pid}/{ps}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pid|pid|path|true|integer(int32)||
|ps|ps|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|Result«List«Video»»|
|401|Unauthorized||
|403|Forbidden||
|404|Not Found||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|data||array|Video|
|&emsp;&emsp;aid|视频id|integer(int64)||
|&emsp;&emsp;coin|投币数|integer(int32)||
|&emsp;&emsp;danmaku|视频弹幕数|integer(int32)||
|&emsp;&emsp;desc|视频描述|string||
|&emsp;&emsp;favorite|收藏数|integer(int32)||
|&emsp;&emsp;like|点赞数|integer(int32)||
|&emsp;&emsp;pic|视频封面图片链接|string||
|&emsp;&emsp;pid|视频分类id|integer(int32)||
|&emsp;&emsp;reply|视频评论数|integer(int32)||
|&emsp;&emsp;share|分享数|integer(int32)||
|&emsp;&emsp;tid|视频类型id|integer(int32)||
|&emsp;&emsp;title|视频标题|string||
|&emsp;&emsp;tname|视频分类名|string||
|&emsp;&emsp;view|视频播放量|integer(int32)||
|message||string||
|state||integer(int32)|integer(int32)|
|success||boolean||


**响应示例**:
```javascript
{
	"data": [
		{
			"aid": 3581515,
			"coin": 25,
			"danmaku": 2761,
			"desc": "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...",
			"favorite": 78,
			"like": 3564,
			"pic": "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png",
			"pid": 11,
			"reply": 1,
			"share": 5,
			"tid": 185,
			"title": "【国产】亲爱的【28集全】",
			"tname": "国产剧",
			"view": 40107
		}
	],
	"message": "",
	"state": 0,
	"success": true
}
```