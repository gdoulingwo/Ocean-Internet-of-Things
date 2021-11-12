<template>
    <div class="container">
       
        <div class="header">
          <div class="logo">
            <img src="../imgs/领沃logo.png" alt="" class="logoImg">
          </div>
          <div class="title">环境监测</div>
          <div class="timeShow" >
            {{currentTime}}
            </div>
        </div>
        <div class="mainContainer">
          <div class="main">
            <!-- 地图 -->
            <div :class="['mapEchartBox',fullScreenStatus.mapEchartBox ? ' fullScreen':'']">
              <div class="resize">
                <span @click="changeSize('mapEchartBox')" :class="['iconfont', fullScreenStatus.mapEchartBox ? 'icon-shousuo':'icon-quanping']"></span>
              </div>
              <div class="mapEchart">
                <baidu-map
                 class="baiduMap" :center="center" :zoom="zoom" @ready="mapHandler" 
                 scroll-wheel-zoom
                 >

             
                 <bm-marker 
                  v-for="item in markerArray"
                  :key="item.id"
                 :position="{lng:item.longitude,lat:item.latitude}" 
                 :dragging="false" 
                 @click="sensorMapMarkerClick(item.id)">
                  <bm-info-window 
                  :show="item.show" @close="sensorMapMarkerClose(item.id)" @open="sensorMapMarkerOpen(item.id)">
                    <h3>{{item.name}}</h3> 
                    <p>传感器基本信息</p>
                  </bm-info-window>
                </bm-marker>
                
                </baidu-map>
              </div>
            </div>

            <!-- 详细信息表格 -->
            <div :class="['deatileMessageBox',fullScreenStatus.deatileMessageBox ? 'fullScreen':'']">
              <div class="resize">
                <span @click="changeSize('deatileMessageBox')" :class="['iconfont', fullScreenStatus.deatileMessageBox ? 'icon-shousuo':'icon-quanping']"></span>
              </div>
              <div class="deatileMessage">
                <div class="deatileMessageTitle">
                  <h1>详细信息</h1>
                  </div> 
                <div class="deatileTable">
                  <el-table
                    :data="tableData"
                    style="width: 100%"
                    height="100%"
                    stripe
                     >
                    <el-table-column
                      fixed
                      :prop="deatileMessageprop[0]"
                      :label="deatileMessagelabel[0]"
                      >
                    </el-table-column>
                    <el-table-column
                      fixed
                      :prop="deatileMessageprop[1]"
                      :label="deatileMessagelabel[1]"
                      >
                    </el-table-column>
                    <el-table-column
                      fixed
                      :prop="deatileMessageprop[2]"
                      :label="deatileMessagelabel[2]"
                     >
                    </el-table-column>
                  </el-table>
                </div>
              </div>
            </div>

            <!-- 搜索传感器表格 -->
            <div :class="['sensorSearchBox',fullScreenStatus.sensorSearchBox ? 'fullScreen':'']">
              <div class="resize">
                <span @click="changeSize('sensorSearchBox')" :class="['iconfont', fullScreenStatus.sensorSearchBox ? 'icon-shousuo':'icon-quanping']"></span>
              </div>
              <div class="sensorSearch">
                  <div class="searchInput">
                    <el-select v-model="sensorSearchValue"  collapse-tags
                      style="margin-left: 20px;" placeholder="请选择传感器" @change="changeSensorTable">
                      <el-option
                        v-for="item in sensorTableOptions"
                        :key="item.id"
                        :label="item.label"
                        :value="item.id">
                      </el-option>
                    </el-select>

                    <!-- <el-select
                      v-model="areaSearchValue"
                      multiple
                      collapse-tags
                      style="margin-left: 20px;"
                      placeholder="请选择区域"
                      @change="changeAreaTable">
                      <el-option
                        v-for="item in areaTableOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select> -->
                  </div>
                  <div class="sensorSearchTable">
                     <el-table
                    :data="tableData"
                    style="width: 100%"
                    height="100%"
                    stripe
                     >
                    <el-table-column
                      fixed
                      :prop="sensorSearchprop[0]"
                      :label="sensorSearchlabel[0]"
                      >
                    </el-table-column>
                    <el-table-column
                      fixed
                      :prop="sensorSearchprop[1]"
                      :label="sensorSearchlabel[1]"
                      >
                    </el-table-column>
                    <el-table-column
                      fixed
                      :prop="sensorSearchprop[2]"
                      :label="sensorSearchlabel[2]"
                     >
                    </el-table-column>
                  </el-table>
                  </div>
              </div>
            </div>

            <!-- 数据数字画像 -->
            <div :class="['dataEchartBox',fullScreenStatus.dataEchartBox ? ' fullScreen':'']" >
              <div class="resize">
                <span @click="changeSize('dataEchartBox')" :class="['iconfont', fullScreenStatus.dataEchartBox ? 'icon-shousuo':'icon-quanping']"></span>
              </div>
              <div class="dataEchart">
                <div class="dataSelect">
                  <el-select v-model="dataEchartValue" placeholder="请选择数据" @change="changeDataEchart">
                      <el-option
                        v-for="item in dataEchartOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </div> 
              <div id="chart1"></div>
              </div>
            </div>
          </div>
        </div> 
    </div>  
</template>
<script type="text/javascript">
import * as echarts from "echarts";
export default {
  data(){
    return{
      name: "envWatch",
      //传感器数组
      markerArray:[],
      //详细信息表格表头
      deatileMessageprop:['coordinate','height','temperature'],
      deatileMessagelabel:['坐标','高度','温度'],
      //传感器表格表头
      sensorSearchprop:['coordinate','height','temperature'],
      sensorSearchlabel:['坐标','高度','温度'],
      timer: "",//定义一个定时器的变量
      currentTime: new Date(), // 获取当前时间
      //百度地图属性
      center: {lng: 0, lat: 0},
      zoom: 3,
      //定义全屏状态
      fullScreenStatus:{
        mapEchartBox:false,
        deatileMessageBox:false,
        sensorSearchBox:false,
        dataEchartBox:false,
      },
      //详细信息表格数据
       tableData: [],
        //传感器搜索表格数据
         sensorTableOptions: [],
        //区域搜索表格数据
        //  areaTableOptions: [{
        //   value: '选项1',
        //   label: '黄金糕'
        // }, ],
        //数字画像选项
        dataEchartOptions: [
          {
          value: 1,
          label: '温度变化'
        }, {
          value: 2,
          label: '风速变化'
        }, ],
        dataEchartValue: '',
        sensorSearchValue: [],
        areaSearchValue: [],
          //echart温度变化数据
       dataEchartOption1 : {
            xAxis: {
              type: 'category',
              data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            },
            yAxis: {
              name:'温度℃',
              type: 'value'
            },
            series: [
              {
               data: [12, 16, 17, 16, 17, 18, 17],
                type: 'line'
              }
            ]
        },
        //echart风速变化数据
        dataEchartOption2: {
         xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
          },
          yAxis: {
            name:'风速KM/H',
            type: 'value'
          },
          series: [
            {
              data: [200, 180, 250, 280, 270, 210, 300],
              type: 'bar',
              showBackground: true,
              backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
              }
            }
          ]
      },
      
        //表格实时刷新数据定时器
        tableTimer:''
    }
  },
  methods: {
    sensorMapMarkerClick(id) {
      var that=this;
      console.log('点击了传感器'+id);
        for(var i=0;i<that.markerArray.length;i++){
          if(that.markerArray[i].id===id){
            that.markerArray[i].show = true;
          }
      };


      //清空表格数据
      that.tableData=[];
      //清除表格刷新定时器
      if (this.tableTimer) {
      clearInterval(this.tableTimer);
    }
      // this.markerArray[0].show = true;
      this.tableTimer = setInterval(function() {
        var xhr=new XMLHttpRequest()
          xhr.open('GET','http://linkworld.natapp1.cc/live/sensor/data?config_id='+id)
          xhr.send(null)
          xhr.onreadystatechange=function(){
            if(this.readyState===4){
              // console.log(this.response);
              //  var json = eval('(' + this.response + ')');
              var json=JSON.parse(this.response)
               var suData=json.data;
              //  console.log('带id请求的数据',suData);
               var suDataJson= eval('(' + suData.data + ')');
               var suDataMean=eval('(' + suData.mean + ')');

                //获取的数据值
               var suDataJsonValue=Object.values(suDataJson);
              //  console.log('带id请求的数据值',suDataJsonValue);
                //获取的数据值的key
               var suDataJsonKey=Object.keys(suDataJson);
                // console.log('带id请求的数据值的key',suDataJsonKey);
                //获取表头意义的值
                // console.log('带id请求的数据意义的值',suDataMean);
                var suDataMeanValue=Object.values(suDataMean);
                 //获取表头意义的key
                // console.log('带id请求的数据意义的key',suDataMeanKey);
                var suDataMeanKey=Object.keys(suDataMean);
                //清空表头和数据
                that.deatileMessageprop=[];
                that.deatileMessagelabel=[];
                that.sensorSearchprop=[];
                that.sensorSearchlabel=[];
                //渲染表头
                that.deatileMessageprop.push('coordinate');
                 for(var i=0;i<suDataMeanValue.length;i++){
                  that.deatileMessageprop.push(suDataMeanKey[i])
                };
                that.deatileMessagelabel.push('坐标');
                for(var i=0;i<suDataMeanValue.length;i++){
                  that.deatileMessagelabel.push(suDataMeanValue[i])
                };
                that.sensorSearchprop.push('coordinate');
                 for(var i=0;i<suDataMeanValue.length;i++){
                  that.sensorSearchprop.push(suDataMeanKey[i])
                };
                that.sensorSearchlabel.push('坐标');
                for(var i=0;i<suDataMeanValue.length;i++){
                  that.sensorSearchlabel.push(suDataMeanValue[i])
                };
                //渲染表格数据
                var newTableData={
                  coordinate:'('+suData.longitude+','+suData.latitude+')',
                }
                that.center.lng=suData.longitude;
                that.center.lat=suData.latitude;
                for(var i=0;i<suDataJsonKey.length;i++){
                  newTableData[suDataJsonKey[i]]=suDataJsonValue[i];
                }

                //将数据填入表格
                that.tableData.unshift(newTableData);
                if(that.tableData.length==31){
                  that.tableData.pop();
                }
                 console.log(that.tableData.length);
            }
          }
    }, 1000);
        
    },
      sensorMapMarkerOpen(id){
      var that=this;
      console.log('打开了传感器'+id);
      },
    sensorMapMarkerClose(id) {
      var that=this;
       for(var i=0;i<that.markerArray.length;i++){
          if(that.markerArray[i].id===id){
            that.markerArray[i].show = false;
          }
      };
      console.log('关闭了传感器'+id);
    },

    // changeAreaTable(data){
    //   console.log(data);
    // },
    changeSensorTable(data){
      
      console.log('改变了传感器',data);
      this.sensorMapMarkerClick(data);
      this.sensorMapMarkerOpen(data)
    },
    changeDataEchart(data){
      console.log(data);
      if(data==1){
      this.initChart(this.dataEchartOption1);  
      }  
      if(data==2){
        this.initChart(this.dataEchartOption2);  
      }
      
    },
    //初始化地图
     mapHandler ({BMap, map}) {
      console.log(BMap, map)
      //设置初始化地图定位
      //经度
      this.center.lng = 110.297
      //纬度
      this.center.lat = 21.152
      //放大倍数
      this.zoom = 15
    },
    changeSize(changeBox){
      this.fullScreenStatus[changeBox]=!this.fullScreenStatus[changeBox];
      var that=this;
      this.$nextTick(()=>{
        // this.screenAdapter();   
        // myChart1.resize();
      console.log(that.initChart);
        // myChart1.clear();
        // myChart1.setOption(option1,true);
      })
    },
   handleForward(key, keyPath) {
      this.$router.push("/page1");
    },
    initChart(option) {
      // 基于准备好的dom，初始化echarts实例
      var myChart1 = echarts.init(document.getElementById("chart1"));
      //获取到数据之前，显示加载动画
      myChart1.showLoading();
      //获取到数据之后，隐藏加载动画
      myChart1.hideLoading();
      // 使用刚指定的配置项和数据显示图表。
      myChart1.setOption(option);
      //设置图表自适应父盒子大小
      window.addEventListener('resize',function(){
        myChart1.resize();
      })
    },

  },
  created(){
    //实时时间显示
    var that = this; //声明一个变量指向Vue实例this，保证作用域一致
    this.timer = setInterval(function() {
      that.currentTime = //修改数据date
        new Date().getFullYear() +
        "/" +
        (new Date().getMonth() + 1) +
        "/" +
        new Date().getDate() +
        "    " +
        new Date().getHours() +
        ":" +
        new Date().getMinutes() +
        " " +
        new Date().getSeconds();
    }, 1000);
    
  //请求传感器数据
  var xhr=new XMLHttpRequest()
          xhr.open('GET','http://linkworld.natapp1.cc/live/sensor')
          xhr.send(null)
          xhr.onreadystatechange=function(){
            if(this.readyState===4){             
                console.log(that.deatileMessagelabel);
              // console.log(this.response);
              var json = eval('(' + this.response + ')'); 
              that.markerArray=json.data;
              console.log('that.markerArray',that.markerArray);
              for(var i=0;i<that.markerArray.length;i++){
                that.markerArray[i].show=false;
                let newSensor={
                  id:that.markerArray[i].id,
                  label:that.markerArray[i].name,
                }
                that.sensorTableOptions.push(newSensor)
              }
              // console.log('这是渲染后的数据');
              // console.log(that.markerArray);
            }
          }
  },
  
  mounted() { 
    //BaiduMap组件及其所有子组件的渲染只能是异步的
    this.changeSize('mapEchartBox');
    this.changeSize('mapEchartBox');
  },
   beforeDestroy() {
     if (this.timer) {
      clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
    }
  },
};
</script>

<style lang="less" scoped>
*{ 
  margin:0;  
  padding: 0; 
  box-sizing: border-box;
}
@font-face {
  font-family: "iconfont"; /* Project id 2920130 */
  src: url('//at.alicdn.com/t/font_2920130_8f4ceab8imx.woff2?t=1636252792434') format('woff2'),
       url('//at.alicdn.com/t/font_2920130_8f4ceab8imx.woff?t=1636252792434') format('woff'),
       url('//at.alicdn.com/t/font_2920130_8f4ceab8imx.ttf?t=1636252792434') format('truetype');
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-shousuo:before {
  content: "\e81f";
}

.icon-quanping:before {
  content: "\e600";
}
// 去掉百度地图左下角水印的两个样式
/deep/.BMap_cpyCtrl {
    display: none!important;
}
/deep/.anchorBL {
    display: none!important;
}
//全屏样式定义
.fullScreen{
  position: fixed !important;
  top: 47px !important;
  width: 91% !important;
  height: 93% !important;
  margin: 0 !important;
  left: 50% !important;
  transform:translate(-50%,0%) !important;
  z-index: 999;
}

.container{
  position: fixed!important;
  top: 0 !important;
  left: 0 !important;
  width: 100% !important;
  height: 92% !important;
  margin: 0 !important;
  z-index: 100;

}
.header{
  height: 50px;
  width: 100%;
  background-color: #fff;
  border-bottom: 1px solid rgb(180, 180, 180);
}
.logo{
  height: 50px;
  width: 10%;
  top: 0;
  left: 2%;
  line-height: 50px;
  position:absolute;
}
.logoImg{
  margin-left:20%;
  height: 48px;
  width: 100px;
}
.title{
  height: 50px;
  position:absolute;
  top: 0;
  left: 50%;
  text-align: center;
  transform:translate(-50%,0%);
  width: 200px;
  line-height: 60px;
  font-size: 24px;
  top: 0;
  color: rgb(161, 161, 161);
}
.timeShow{
  height: 50px;
  right: 10%;
  line-height: 50px;
  top: 0;
  position:absolute;
}
.mainContainer{
  width: 100%;
  height: 100%;
}
.main{
  width: 90%;
  margin: auto;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  position:relative
}
.mapEchartBox ,.deatileMessageBox ,.sensorSearchBox ,.dataEchartBox{
  padding: 3px;
  flex: 50%;
  display: inline-block;
  min-height: 50%;
  position: relative;
}
.mapEchart{
  border: 1px solid rgb(192, 192, 192);
  background-color: #fff;
  width: 100%;
  height: 100%;
  display: inline-block;
}
.deatileMessage{
  border: 1px solid rgb(192, 192, 192);
  background-color: #fff;
  width: 100%;
  height: 100%;
}
.sensorSearch{
  border: 1px solid rgb(192, 192, 192);
  background-color: #fff;
  width: 100%;
  height: 97%;
}
.dataEchart{
  border: 1px solid rgb(192, 192, 192);
  background-color: #fff;
  width: 100%;
  height: 97%;
  display: inline-block;
}
#chart1{
  width: 90%;
  height: 90%;
  margin-left: 10%;
  display: inline-block;
}
.resize{
  position:absolute;
  right: 15px;
  top: 15px;
  cursor: pointer;
  z-index: 900;
}
.deatileMessageTitle{
  padding: 5px;
}
.deatileTable{
  width: 98%;
  height: 81%;
  position:absolute;
}
.searchInput{
  padding: 5px;
}
.sensorSearchTable{
  width: 98%;
  height: 80%;
  position:absolute;
}
.baiduMap{
  width: 100%;
  height: 100%;
}
.dataSelect{
  padding: 5px;
}

</style>