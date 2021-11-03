# 报警规则补充说明



## 参数说明

##### msgTemplate

消息模板，可用 `{}` 的形式将其替换为在同一筛选器中的出现的属性值



##### EnableType

启用的时间模式

>ALWAYS_ON ：总是开启
> 
>FIXED_TIME：固定时间段，需在extend中添加from属性和to属性
> 
>FLEXIBLE_TIME：灵活时间段，需在extend中添加from属性和to属性



##### EnableCondition

启动的条件

>ONCE：满足一次条件就启动
>
>CONTINUE：触发持续到指定时间才启动，需在extend中添加time属性和timeUnit属性
>
>REPEAT：触发固定次数才启动，需添加time属性



##### Connective

```java
EQ,     // 等于
NEQ,    // 不等于
GT,     // 大于
LT,     // 小于
EGT,    // 大于或等于
ELT,    // 小于或等于
SA,     // 开始于
EA,     // 结束于
LI,     // 包含于
NI      // 不包含
```



##### 修改等级

报警规则的更新等级分为四级，初始等级是四级，前端需要记录用户的修改操作，当做出不满足当前等级的行为时进行降级

一级：全量传入参数，本质是删除并重新插入

二级：全量传入一个或多个键筛选器

三级：缺省传入被修改的规则的和其键筛选器的数据上的变化值

四级：修改报警类型



## 请求说明



##### 添加数据

场景：添加一个水温异常类型的警告，第一个消息模板是报警模板，警告等级是ERROR，只在每天早上九点到晚上九点进行监控，当触发规则持续五分钟的时候进行报警，规则是(当当前温度大于最大温度属性或小于最小温度属性属性)且不等于100度的且同时watch_enable属性还需要为true才为通过，第二个消息模板是清除报警的模板，警告级别同样为ERROR，触发时间段和触发方式相同，且当当前温度小于最大温度且大于最小温度才通过

请求示例：

```json
  {
    "alarmType": "水温异常",
    "alarmRules": [
      {
        "level": "ERROR",
        "msgTemplate": "当前温度可能超过或低于{temperature}度",
        "keyFilters": [
          {
            "key": "tempperature",
            "valueType": "DOUBLE",
            "decisionNode": {
              "nodeId": 1,
              "isAnd": true,
              "son": [
                {
                  "nodeId": 2,
                  "isAnd": false,
                  "son": [
                    {
                      "nodeId": 3,
                      "expression": {
                        "connective": "GT",
                        "value": "myMaxTemperature",
                        "isDynamic": true
                      }
                    },
                    {
                      "nodeId": 4,
                      "expression": {
                        "connective": "LT",
                        "value": "myMinTemperature",
                        "isDynamic": true
                      }
                    }
                  ]
                },
                {
                  "nodeId": 5,
                  "expression": {
                    "connective": "NEQ",
                    "value": "100.0",
                    "isDynamic": false
                  }
                }
              ]
            }
          },
          {
            "key": "watch_enable",
            "valueType": "BOOLEAN",
            "decisionNode": {
              "nodeId": 1,
              "expression": {
                "connective": "EQ",
                "value": "true",
                "isDynamic": false
              }
            }
          }
        ],
        "enableType": "FIXED_TIME",
        "enableCondition": "CONTINUE",
        "extend": "{\"from\": \"9:00:00\", \"to\": \"21:00:00\", \"time\": \"5\", \"timeUnit\": \"MINUTES\"}",
        "isReset": false
      },
      {
        "level": "ERROR",
        "msgTemplate": "当前温度已经回复到{temperature}度",
        "keyFilters": [
          {
            "key": "tempperature",
            "valueType": "DOUBLE",
            "decisionNode": {
              "nodeId": 1,
              "isAnd": true,
              "son": [
                {
                  "nodeId": 2,
                  "expression": {
                    "connective": "LT",
                    "value": "myMaxTemperature",
                    "isDynamic": true
                  }
                },
                {
                  "nodeId": 3,
                  "expression": {
                    "connective": "GT",
                    "value": "myMinTemperature",
                    "isDynamic": true
                  }
                }
              ]
            }
          }
        ],
        "enableType": "FIXED_TIME",
        "enableCondition": "CONTINUE",
        "extend": "{\"from\": \"9:00:00\", \"to\": \"21:00:00\", \"time\": \"5\", \"timeUnit\": \"MINUTES\"}",
        "isReset": true
      }
    ]
  }
```



##### 查询接口的返回样式

```json
{
    "code": 0,
    "msg": "success",
    "data": [
      {
        "alarmType": "水温异常",
        "alarmRules": [
          {
            "ruleId": 10,
            "level": "ERROR",
            "msgTemplate": "当前温度可能超过或低于{temperature}度",
            "keyFilters": [
              {
                "key": "tempperature",
                "valueType": "DOUBLE",
                "decisionNode": {
                  "treeId": 19,
                  "nodeId": 1,
                  "expression": null,
                  "isAnd": true,
                  "son": [
                    {
                      "treeId": 19,
                      "nodeId": 2,
                      "expression": null,
                      "isAnd": false,
                      "son": [
                        {
                          "treeId": 19,
                          "nodeId": 3,
                          "expression": {
                            "id": 18,
                            "connective": "GT",
                            "value": "myMaxTemperature",
                            "isDynamic": true
                          },
                          "isAnd": false,
                          "son": []
                        },
                        {
                          "treeId": 19,
                          "nodeId": 4,
                          "expression": {
                            "id": 19,
                            "connective": "LT",
                            "value": "myMinTemperature",
                            "isDynamic": true
                          },
                          "isAnd": false,
                          "son": []
                        }
                      ]
                    },
                    {
                      "treeId": 19,
                      "nodeId": 5,
                      "expression": {
                        "id": 20,
                        "connective": "NEQ",
                        "value": "100.0",
                        "isDynamic": false
                      },
                      "isAnd": false,
                      "son": []
                    }
                  ]
                },
                "treeId": 19,
                "ruleId": 10
              },
              {
                "key": "watch_enable",
                "valueType": "BOOLEAN",
                "decisionNode": {
                  "treeId": 20,
                  "nodeId": 1,
                  "expression": {
                    "id": 21,
                    "connective": "EQ",
                    "value": "true",
                    "isDynamic": false
                  },
                  "isAnd": false,
                  "son": []
                },
                "treeId": 20,
                "ruleId": 10
              }
            ],
            "enableType": "FIXED_TIME",
            "enableCondition": "CONTINUE",
            "extend": "{\"from\": \"9:00:00\", \"to\": \"21:00:00\", \"time\": \"5\", \"timeUnit\": \"MINUTES\"}",
            "isReset": false
          },
          {
            "ruleId": 11,
            "level": "ERROR",
            "msgTemplate": "当前温度已经回复到{temperature}度",
            "keyFilters": [
              {
                "key": "tempperature",
                "valueType": "DOUBLE",
                "decisionNode": {
                  "treeId": 21,
                  "nodeId": 1,
                  "expression": null,
                  "isAnd": true,
                  "son": [
                    {
                      "treeId": 21,
                      "nodeId": 2,
                      "expression": {
                        "id": 22,
                        "connective": "LT",
                        "value": "myMaxTemperature",
                        "isDynamic": true
                      },
                      "isAnd": false,
                      "son": []
                    },
                    {
                      "treeId": 21,
                      "nodeId": 3,
                      "expression": {
                        "id": 23,
                        "connective": "GT",
                        "value": "myMinTemperature",
                        "isDynamic": true
                      },
                      "isAnd": false,
                      "son": []
                    }
                  ]
                },
                "treeId": 21,
                "ruleId": 11
              }
            ],
            "enableType": "FIXED_TIME",
            "enableCondition": "CONTINUE",
            "extend": "{\"from\": \"9:00:00\", \"to\": \"21:00:00\", \"time\": \"5\", \"timeUnit\": \"MINUTES\"}",
            "isReset": true
          }
        ],
        "profileId": 1
      }
    ]
  }
```



##### 插入规则

在"水温异常"的报警类型下插入一条规则，在早上九点到晚上九点，当水温小于或等于60.0度即触发规则

```json
{
  "level": "INFO",
  "msgTemplate": "当前温度保持正常",
  "keyFilters": [
    {
      "key": "tempperature",
      "valueType": "DOUBLE",
      "decisionNode": {
        "nodeId": 1,
        "expression": {
          "connective": "ELT",
          "value": "60.0",
          "isDynamic": false
        }
      }
    }
  ],
  "enableType": "FIXED_TIME",
  "enableCondition": "ONCE",
  "extend": "{\"from\": \"9:00:00\", \"to\": \"21:00:00\"}",
  "isReset": false
}
```



##### 三级修改

第一个决策树的节点只携带树的id，然后再在其子节点传入被修改的数据的同时携带唯一标识，其子节点没有结构顺序

例：

```json
{
  "keyFilters": [
    {
      "decisionNode": {
        "treeId": 30, 
        "son": [
          {
            "nodeId": 2,
            "expression": {
              "id": 32,
              "value": "40.0"
            }
          }          
        ]
      }
    }
  ],
  "enableType": "FIXED_TIME",
  "extend": "{\"mon\":{\"from\":\"9:00:00\",\"to\":\"21:00:00\"},\"tue\":{\"from\":\"9:00:00\",\"to\":\"21:00:00\"},\"wed\":{\"from\":\"9:00:00\",\"to\":\"21:00:00\"},\"thu\":{\"from\":\"9:00:00\",\"to\":\"21:00:00\"},\"fri\":{\"from\":\"9:00:00\",\"to\":\"21:00:00\"},\"sat\":{\"from\":\"00:00:00\",\"to\":\"00:00:00\"},\"sum\":{\"from\":\"00:00:00\",\"to\":\"00:00:00\"}}"
}
```



##### 二级修改

例：去掉了最小温度和100度，同时保持监控属性

```json
{
  "keyFilters": [
    {
      "key": "tempperature",
      "valueType": "DOUBLE",
      "decisionNode": {
        "nodeId": 1,
        "isAnd": true,
        "son": [
          {
            "nodeId": 2,
            "isAnd": false,
            "son": [
              {
                "nodeId": 3,
                "expression": {
                  "connective": "GT",
                  "value": "myMaxTemperature",
                  "isDynamic": true
                }
              }
            ]
          }
        ]
      }
    },
    {
      "key": "watch_enable",
      "valueType": "BOOLEAN",
      "decisionNode": {
        "nodeId": 1,
        "expression": {
          "connective": "EQ",
          "value": "true",
          "isDynamic": false
        }
      }
    }
  ]
}
```

