# 规则链

## 规则链细节更新

PUT：/api/rule/node/detail/{ruleChainUuid}

例子1：

```json
{
  "firstNodeIndex": 0,
  "nodes": [
    {
      "name": "TestFilterNode",
      "uuid": "e75ab17a-550f-36ad-bbe8-91b819cf951d",
      "additionalInfo": "{}",
      "type": "org.linkworld.ocean.manager.engine.components.filter.SwitchFilterNode",
      "configuration": "{}",
      "layout": "{}"
    }
  ],
  "connections": [
  ],
  "ruleChainConnections": {}
}
```

[![gtQPNd.png](https://z3.ax1x.com/2021/05/10/gtQPNd.png)](https://imgtu.com/i/gtQPNd)

例子2：

```json
{
  "firstNodeIndex": 0,
  "nodes": [
    {
      "name": "TestFilterNode",
      "uuid": "e75ab17a-550f-36ad-bbe8-91b819cf951d",
      "additionalInfo": "{}",
      "type": "org.linkworld.ocean.manager.engine.components.filter.SwitchFilterNode",
      "configuration": "{}",
      "layout": "{}"
    },
    {
      "name": "new-test",
      "uuid": "fawe123-dd234-15sv-dva8-rg1239dff54cd",
      "additionalInfo": "{}",
      "type": "org.linkworld.ocean.manager.engine.components.filter.OtherRuleChainNode",
      "configuration": "{}",
      "layout": "{}"
    }
  ],
  "connections": [
    {
      "fromIndex": 0,
      "toIndex": 1,
      "types": [
        "关系1",
        "关系2"
      ]
    }
  ],
  "ruleChainConncetions": {
    "uuid": "engine-dd234-91b819cf951d-dva8-rg1239dff54cd",
    "name": "new-test"
  }
}
```

[![gtQnHg.png](https://z3.ax1x.com/2021/05/10/gtQnHg.png)](https://imgtu.com/i/gtQnHg)

带 * 的为必填项,

参数说明：

* *firstNodeIndex：首个node节点在nodes的索引（数组索引）
* nodes：用于存放节点
    * *name：用户自定义的节点名称
    * *uuid：节点的uuid，没有的话就不填
    * *additionalInfo：JSON格式的**额外数据**，**必须是字符形式**
    * *type：即此类在后台中的类标识
    * *configuration：JSON格式的**配置数据**，**必须是字符形式**
    * *layout：JSON格式的**画布布局**，**必须是字符形式**，里面具体的key value后续商量
* connections：用于存放节点之间的连接
    * id：连接的id
    * fromIndex：节点在nodes里的索引
    * toIndex：节点在nodes里的索引
    * type：用于存放它们的关系
* *ruleChainConncetions：用于存放其他规则链


