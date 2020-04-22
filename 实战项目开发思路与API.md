## 项目开发思路

### 一.设计数据库

~~~ sql
CREATE TABLE `student` (
  `stu_id` INT(8) NOT NULL COMMENT '学生学号',
  `stu_name` varchar(8) NOT NULL COMMENT '学生姓名',
  `stu_major` smallint(2) NOT NULL COMMENT '学生所属专业 用1位整形数字表示',
  `stu_class` smallint(2) NOT NULL COMMENT '学生班级 用1位整形数字表示',
  `stu_phone` varchar(11) NOT NULL COMMENT '手机号',
  `stu_qq` varchar(11) DEFAULT NULL COMMENT 'QQ号',
  `stu_status` varchar(10) DEFAULT '未录取',
  `org_id` smallint(5) DEFAULT NULL COMMENT'所属学生组织id',
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE major(
		major_id SMALLINT(8) NOT NULL PRIMARY KEY COMMENT'专业编号',
		major_name VARCHAR(8) NOT NULL COMMENT'专业名称'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE report(
		report_id SMALLINT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'志愿编号',
		student_id INT(8) NOT NULL UNIQUE KEY COMMENT'学生学号',
		vol_first SMALLINT(2) NOT NULL COMMENT'组织编号1',
		vol_second SMALLINT(2) DEFAULT NULL COMMENT'组织编号2',
  	reason_first VARCHAR(200) NOT NULL COMMENT'申请第一志愿理由',
  	reason_second VARCHAR(200) NOT NULL COMMENT'申请第二志愿理由',
  	is_dispensing smallint(1) NOT NULL COMMENT'是否接受调剂',
  	remark varchar(300) DEFAULT NULL COMMENT'面试备注',
  	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE organization(
		org_id SMALLINT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'组织编号',
		org_name VARCHAR(20) NOT NULL COMMENT'组织名称',
  	branch_name VARCHAR(20) COMMENT'分支组织名称'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user(
		user_id SMALLINT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT'用户编号',
		user_name VARCHAR(12) NOT NULL UNIQUE KEY COMMENT'用户名',
		user_password VARCHAR(15) NOT NULL COMMENT'密码',
   	org_id smallint(2) DEFAULT NULL COMMENT'所属学生组织id',
		user_status SMALLINT(1) NOT NULL COMMENT'用户权限 1为管理员 2为超级管理员',
  	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~

约束添加

~~~sql
/*学生专业->专业id*/
ALTER TABLE student ADD CONSTRAINT FOREIGN KEY(stu_major) REFERENCES major(major_id) ON UPDATE CASCADE;

/*填报志愿1->组织id*/
ALTER TABLE report ADD CONSTRAINT FOREIGN KEY(vol_first) REFERENCES organization(org_id) ON UPDATE CASCADE;

/*填报志愿2->组织id*/
ALTER TABLE report ADD CONSTRAINT FOREIGN KEY(vol_second) REFERENCES organization(org_id) ON UPDATE CASCADE;

/*志愿表学生学号->学生表id*/
ALTER TABLE report ADD CONSTRAINT FOREIGN KEY(student_id) REFERENCES student(stu_id) ON UPDATE CASCADE;

/*管理员所属组织->组织id*/
ALTER TABLE user ADD CONSTRAINT FOREIGN KEY(org_id) REFERENCES organization(org_id) ON UPDATE CASCADE;

/*学生录取后所属组织*/
ALTER TABLE student ADD CONSTRAINT FOREIGN KEY(org_id) REFERENCES organization(org_id) ON UPDATE CASCADE;
~~~



建视图

~~~sql
select `a`.`stu_id` AS `stu_id`,
`a`.`stu_name` AS `stu_name`,
`b`.`major_name` AS `major_name`,
`a`.`stu_class` AS `stu_class`,
`a`.`stu_phone` AS `stu_phone`,
`a`.`stu_qq` AS `stu_qq`,
`d`.`org_name` AS `org_first`,
`d`.`branch_name` AS `bra_first`,
`e`.`org_name` AS `org_second`,
`e`.`branch_name` AS `bra_second`,
`c`.`reason_first` AS `reason_first`,
`c`.`reason_second` AS `reason_second`,
`c`.`is_dispensing` AS `is_dispensing`,
`a`.`stu_status` AS `stu_status`,
`c`.`update_time` AS `update_time`,
`c`.`create_time` AS `create_time` ,
`c`.`remark` AS `remark`
from ((((`student` `a` left join `major` `b` on((`a`.`stu_major` = `b`.`major_id`))) left join `report` `c` on((`a`.`stu_id` = `c`.`student_id`))) left join `organization` `d` on((`c`.`vol_first` = `d`.`org_id`))) left join `organization` `e` on((`c`.`vol_second` = `e`.`org_id`)))
~~~



统计视图

~~~sql
select count(0) AS `total_report`,
sum((`a`.`org_first` = '团委')) AS `tuanwei_first`,
sum((`a`.`org_second` = '团委')) AS `tuanwei_second`,
sum((`a`.`org_first` = '学生会')) AS `xueshenghui_first`,
sum((`a`.`org_second` = '学生会')) AS `xueshenghui_second`,
sum((`a`.`org_first` = '科技协会')) AS `kexie_first`,
sum((`a`.`org_second` = '科技协会')) AS `kexie_second`,
sum((`a`.`org_first` = '勤工助学中心')) AS `zhuxue_first`,
sum((`a`.`org_second` = '勤工助学中心')) AS `zhuxue_second`,
sum((`a`.`org_first` = '新闻中心')) AS `xinwen_first`,
sum((`a`.`org_second` = '勤工助学中心')) AS `xinwen_second`
from `report_view` `a`
~~~



### 二.API设计



各部门详情:

***

"团委":"组织部","宣传部","心协","青协"

***

"学生会":"办公室","学习部","宣传部","文艺部","体育部","外联部","自管会"

***

"科技协会":"科技协会"

***

"勤工助学中心":"管理部","活动部","助贷部","外联部"

***

"新闻中心":"摄影协会","新媒体部","记者团"



关于学生录取状态情况：

未被录取

一志愿录取

二志愿录取



#### 账号管理

##### 1.0 登录

[POST] / user / login

参数

| 参数名称 | 是否必须 | 说明   |
| -------- | -------- | ------ |
| username | Y        | 用户名 |
| password | Y        | 密码   |

返回

`````javascript
{
		"code":0,
		"msg":"登录成功",
  	"data":{
      	"username":"用户名",
        "status":"用户权限状态"		//0为普通用户 1位管理员 2为超级管理员
    }
}

{
		"code":1,
		"msg":"登录失败"
}

//同时会在游览器中保存名为username的Cookie，通过Cookie进行验证，前端可通过localStorage保存用户名与权限身份
`````



##### 1.1 注销

[POST] / user / logout

参数

| 参数名称 | 是否必须 | 说明   |
| -------- | -------- | ------ |
| username | Y        | 用户名 |

返回

`````javascript
{
		"code":0,
		"msg":"注销成功"
}

{
		"code":1,
		"msg":"注销失败"
}
`````



#### 志愿信息管理

##### 1.0 报名志愿

[POST] / report / insert

参数

| 参数名称     | 是否必须 | 说明                                          |
| ------------ | -------- | --------------------------------------------- |
| stdId        | Y        | 学生学号 8位 2020开头                         |
| stdName      | Y        | 学生姓名                                      |
| major        | Y        | 专业名                                        |
| classNum     | Y        | 若传入0则在检索第二志愿时过滤不接受调剂的学生 |
| stdQQ        | Y        | 学生的QQ号                                    |
| stdPhone     | Y        | 学生的手机号                                  |
| firstOrg     | Y        | 第一志愿主组织名称                            |
| firstBra     | Y        | 第一志愿分支名                                |
| firstReason  | Y        | 第一志愿理由                                  |
| secondOrg    | N        | 第二志愿主组织名称                            |
| secondBra    | N        | 第二志愿分支名                                |
| secondReason | N        | 第二志愿理由                                  |
| isDispensing | Y        | 是否接受调剂 传入0不接受 传入1接受            |

返回

~~~ JSON
{
		"code":0,
		"msg":"success"
}

{
		"code":1,
		"msg":"验证码错误"
}

{
		"code":2,
		"msg":"第一志愿信息不完整"
}

{
		"code":3,
		"msg":"志愿已填写"
}

{
		"code":4,
		"msg":"姓名为空"
}

//手机号与QQ号、学号要用正则表达式验证，否则后端会抛出异常
~~~



##### 1.1 查询全部志愿(分页实现) 

权限：需要管理员（返回所属主组织全部志愿）/超级管理员（全部）

[GET] / report / getAll

参数

| 参数名称     | 是否必须 | 说明                                                         |
| ------------ | -------- | ------------------------------------------------------------ |
| page         | Y        | 页数                                                         |
| pageSize     | Y        | 一页显示多少条                                               |
| will         | Y        | 1表示第一志愿，2表示第二志愿，0表示两个志愿都查<br />超级管理员可以查全部，普通管理员只能传1或2 |
| isDispensing | N        | 若传入0则在检索第二志愿时过滤不接受调剂的学生                |
| isEnroll     | N        | 传入0查找未录取志愿，传入1则查找已录取志愿                   |

返回

~~~ JSON
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:15,					//总学生数目
				"report_list":[
						{
							"stdId":"20185584"			//学号
							"stdName":"张钊铭", 			 	 //姓名
							"major":"计算机科学与技术",		//专业
							"classNum":"3",						//班级
							"stdQQ":"798998087"				//QQ号
							“stdPhone”:"13800138000",  //手机号
							firstWill:{	//第一志愿信息
        					organization:String, 
        					branch:String, 
        					reason:String
    					},
    					secondWill:{
        					organization:String, 
        					branch:String, 
        					reason:String
    					},
    					isDispensing:Boolean, //是否调剂
  						isEnroll:boolean, 		//是否已录取
							"update_time":"2019-07-26",	//志愿修改时间
							"create_time":"2019-07-26",	//志愿创建时间
						}
				]
		}
}
~~~



##### 1.2 根据学号查询一条志愿详情

权限：需要管理员（返回所属组织志愿信息）/超级管理员（全部）

[GET] / report / getByStdId

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| stdId    | Y        | 学生学号 |

返回

~~~ JSON
{
		"code":0,
		"msg":"success",
		"data":{
							"stdId":"20185584"			//学号
							"stdName":"张钊铭", 			 	 //姓名
							"major":"计算机科学与技术",		//专业
							"classNum":"3",						//班级
							"stdQQ":"798998087"				//QQ号
							“stdPhone”:"13800138000",  //手机号
							firstWill:{	//第一志愿信息
        					organization:String, 
        					branch:String, 
        					reason:String
    					},
    					secondWill:{
        					organization:String, 
        					branch:String, 
        					reason:String
    					},
    					isDispensing:Boolean //是否调剂
 							isEnroll:Boolean, 	 //是否已录取
							"update_time":"2019-07-26",	//志愿修改时间
							"create_time":"2019-07-26",	//志愿创建时间
  						"remark":"备注" 	//备注
			}
}
~~~



##### 1.3 查询录取结果

[GET] /report/search

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| stdId    | Y        | 学生学号 |
| stdName  | Y        | 学生姓名 |
| stdPhone | Y        | 手机号   |

返回

~~~ json
{
		"code":0,
		"msg":"success",
		"data":{
				"stdId":"20185584"			//学号
				"stdName":"张钊铭", 			 	 //姓名
				"major":"计算机科学与技术",		//专业
				"classNum":"3",						//班级
				"status":"未录取",					//录取状态
				"organization":"团委"			//组织名，若未录取则不显示
				"branch":"组织部"					//组织分支名，若未录取则不显示
		}
}
~~~



##### 1.4 根据学号删除一条志愿

权限：需要超级管理员权限

[POST] / report / delete

参数

| 参数名称 | 是否必须 | 说明     |
| -------- | -------- | -------- |
| stdId    | Y        | 学生学号 |

返回

~~~ json
{
		"code":0,
		"msg":"删除成功"
}

{
		"code":1,
		"msg":"删除失败"
}
~~~



##### 1.5 根据学号录取学生

权限：需要管理员/超级管理员权限

[POST] /report/ enroll

参数

| 参数名称     | 是否必须 | 说明             |
| ------------ | -------- | ---------------- |
| stdId        | Y        | 学生学号         |
| organization | Y        | 录取组织名       |
| branch       | Y        | 录取的组织分支名 |

返回

~~~ json
{
		"code":0,
		"msg":"录取成功"
}

{
		"code":1,
		"msg":"录取失败"
}
~~~



##### 1.6 志愿数据分析

权限：需要超级管理员

[POST] /report/ analyse



##### 1.7 数据统计

权限：管理员/超级管理员

[GET]/data/get

返回

~~~json
{
    "code": 0,
    "msg": "success",
    "data": {
        "totalReport": 14,
        "tuanweiFirst": 4,
        "tuanweiSecond": 5,
        "xueshenghuiFirst": 7,
        "xueshenghuiSecond": 5,
        "kexieFirst": 1,
        "kexieSecond": 0,
        "zhuxueFirst": 1,
        "zhuxueSecond": 2,
        "xinwenFirst": 1,
        "xinwenSecond": 2
    }
}
~~~



#### 学生信息管理

##### 1.0查询所有学生信息

权限：需要超级管理员身份，若不是超级管理员身份返回所属组织的录取学生信息

[GET] / student / getAll

参数

| 参数名称 | 是否必须 | 说明           |
| -------- | -------- | -------------- |
| page     | Y        | 页数           |
| pageSize | Y        | 一页显示多少条 |

返回

~~~ JSON
{
		"code":0,
		"msg":"success",
		"data":{
				“total”:1,					//总学生数目
				"student_list":[
						{
							"stdId":"20185584"			//学号
							"stdName":"张钊铭", 			 	 //姓名
							"major":"计算机科学与技术",		//专业
							"classNum":"3",						//班级
							"stdQQ":"798998087"				//QQ号
							“stdPhone”:"13800138000",  //手机号
						}
				]
		}
}
~~~



更多接口等待完善

