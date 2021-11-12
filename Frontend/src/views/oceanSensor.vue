<template>
  <div class="contain">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="首页" name="first">
        <div class="tab">
          <el-tag
            v-for="tag in dynamicTags"
            :key="tag"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
          >
            {{ tag }}
          </el-tag>
          <el-input
            v-if="inputVisible"
            ref="saveTagInput"
            v-model="inputValue"
            class="input-new-tag"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          />
          <el-button v-else class="button-new-tag" size="small" @click="showInput">+</el-button>
        </div>
        <div class="search">
          <el-form ref="formInline" :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item prop="content">
              <el-input v-model="formInline.content" placeholder="搜索框" style="width: 300px;" />
            </el-form-item>
            <el-form-item label="日期" style="margin-left: 50px; font-weight: bold;">
              <el-col :span="11">
                <el-form-item prop="date1">
                  <el-date-picker v-model="formInline.date1" type="date" placeholder="选择日期" style="width: 100%;" />
                </el-form-item>
              </el-col>
              <el-col class="line" :span="2">——</el-col>
              <el-col :span="11">
                <el-form-item prop="date2">
                  <el-time-picker v-model="formInline.date2" placeholder="选择时间" style="width: 100%;" />
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"><i class="el-icon-search" /> 查询</el-button>
              <el-button type="primary" @click="resetForm('formInline')"><i class="el-icon-refresh-left" /> 重置</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="list">
          <div class="function">
            <el-button type="primary" @click="addList"><i class="el-icon-plus" /> 增加</el-button>
            <el-button type="primary" @click="deleteList"><i class="el-icon-delete" /> 删除</el-button>
            <el-button type="primary" @click="reviseList"><i class="el-icon-edit" /> 修改</el-button>
            <el-button type="primary"><i class="el-icon-download" /> 导出</el-button>
            <el-button type="primary" class="cancel" @click="toggleSelection()"><i class="el-icon-refresh-left" /> 取消勾选</el-button>
          </div>
          <el-drawer
            title="添加传感器"
            :visible.sync="drawer"
            :with-header="false"
          >
            <div style="font-size: 20px; font-weight: bold; line-height: 80px; text-align: center;">添加传感器</div>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="名称">
                <el-input v-model="form.name" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="标题">
                <el-input v-model="form.topic" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="风速">
                <el-input v-model="form.wind_speed" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="温度">
                <el-input v-model="form.temperature" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="经度">
                <el-input v-model="form.longitude" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="纬度">
                <el-input v-model="form.latitude" style="width: 250px;" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">创建</el-button>
                <el-button @click="cancel">取消</el-button>
              </el-form-item>
            </el-form>
          </el-drawer>
          <el-drawer
            title="修改传感器"
            :visible.sync="drawer2"
            :with-header="false"
          >
            <div style="font-size: 20px; font-weight: bold; line-height: 80px; text-align: center;">修改传感器</div>
            <el-form ref="form2" :model="form2" label-width="80px">
              <el-form-item label="名称">
                <el-input v-model="form2.name" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="标题">
                <el-input v-model="form2.topic" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="风速">
                <el-input v-model="form2.wind_speed" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="温度">
                <el-input v-model="form2.temperature" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="经度">
                <el-input v-model="form2.longitude" style="width: 250px;" />
              </el-form-item>
              <el-form-item label="纬度">
                <el-input v-model="form2.latitude" style="width: 250px;" />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit2">创建</el-button>
                <el-button @click="cancel2">取消</el-button>
              </el-form-item>
            </el-form>
          </el-drawer>
          <div class="info">
            <el-table
              ref="multipleTable"
              :model="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange"
            >
              <el-table-column type="selection" width="60" />
              <el-table-column prop="name" label="名称" width="160" />
              <el-table-column prop="topic" label="标题" width="160" />
              <el-table-column prop="longitude" label="经度" width="160" />
              <el-table-column prop="latitude" label="纬度" width="160" />
              <el-table-column prop="createTime" label="创建时间" width="160" />
              <el-table-column prop="note" label="备注" show-overflow-tooltip />
            </el-table>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="管理系统" name="second">管理系统</el-tab-pane>
      <el-tab-pane label="传感器管理" name="third">传感器管理</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { showSensor, addSensor, deleteSensor, updateSensor } from '@/api/oceanSensor'
export default {
  name: 'Home',
  data() {
    return {
      activeName: 'first',
      dynamicTags: ['标签一', '标签二', '标签三'],
      inputVisible: false,
      inputValue: '',
      input1: '',
      input2: '',
      input3: '',
      input4: '',
      formInline: {
        content: '',
        date1: '',
        date2: ''
      },
      tableData: [],
      multipleSelection: [],
      showParams: {
        page: 1,
        size: 10
      },
      showSensor: [],
      drawer: false,
      drawer2: false,
      form: {
        name: '',
        wind_speed: '',
        temperature: '',
        longitude: '',
        latitude: '',
        topic: ''
      },
      form2: {
        name: '',
        wind_speed: '',
        temperature: '',
        longitude: '',
        latitude: '',
        topic: ''
      },
      multipleTable: {
        name: '',
        createTime: '',
        longitude: '',
        latitude: '',
        topic: '',
        note: ''
      }
    }
  },
  created() {
    showSensor(this.showParams.page, this.showParams.size)
      .then((res) => {
        console.log(res)
        this.tableData = res
      })
      .catch((error) => {
        console.error(error)
      })
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    },
    resetForm(formName) {
      if (this.$refs[formName] !== undefined) {
        this.$refs[formName].resetFields()
      }
    },
    toggleSelection(rows) {
      this.$refs.multipleTable.clearSelection()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      const now = this.multipleSelection[0]
      this.form2 = {
        name: now.name,
        wind_speed: now.config.wind_speed,
        temperature: now.config.temperature,
        longitude: now.longitude,
        latitude: now.latitude,
        topic: now.topic,
        id: now.id
      }
    },
    onSubmit() {
      const _this = this
      const config = {
        'wind_speed': _this.form.wind_speed,
        'temperature': _this.form.temperature
      }
      const data = {
        'name': _this.form.name,
        'config': JSON.stringify(config),
        'longitude': _this.form.longitude,
        'latitude': _this.form.latitude,
        'topic': _this.form.topic
      }
      const body = JSON.stringify(data)
      // 接口调用
      addSensor(body)
        .then((res) => {
          const config1 = JSON.parse(res.config)
          _this.tableData.push({
            name: res.name,
            createTime: res.createTime,
            longitude: res.longitude,
            latitude: res.latitude,
            topic: res.topic,
            note: res.note,
            id: res.id,
            config: {
              wind_speed: config1.wind_speed,
              temperature: config1.temperature
            }
          })
        })
        .catch((error) => {
          console.error(error)
        })
        .finally(() => {
          location.reload()
        })
    },
    cancel() {
      this.drawer = false
      this.form = {
        name: '',
        wind_speed: '',
        temperature: '',
        longitude: '',
        latitude: '',
        topic: ''
      }
      location.reload()
    },
    addList() {
      this.drawer = true
    },
    deleteList() {
      const deleteId = []
      if (this.multipleSelection) {
        this.multipleSelection.forEach(function(e) {
          deleteId.push(e.id)
        })
      }
      const params = JSON.stringify(deleteId)
      deleteSensor(params)
        .then((res) => {
          console.log(res, '删除成功！')
        })
        .catch((error) => {
          console.error(error)
        })
        .finally(() => {
          location.reload()
        })
    },
    reviseList() {
      this.drawer2 = true
    },
    onSubmit2() {
      const config = {
        'wind_speed': this.form2.wind_speed,
        'temperature': this.form2.temperature
      }
      const data = {
        'id': this.form2.id,
        'name': this.form2.name,
        'config': JSON.stringify(config),
        'longitude': this.form2.longitude,
        'latitude': this.form2.latitude,
        'topic': this.form2.topic
      }
      const body = JSON.stringify(data)
      updateSensor(body)
        .then((res) => {
          console.log('aaa', res)
        })
        .catch((error) => {
          console.log(error)
        })
        .finally(() => {
          location.reload()
        })
    },
    cancel2() {
      this.drawer = false
      this.form2 = {
        name: '',
        wind_speed: '',
        temperature: '',
        longitude: '',
        latitude: '',
        topic: ''
      }
      location.reload()
    }
  }
}
</script>

   <style lang="less" scoped>
   .contain {
     padding-left: 30px;
   }
   .el-tag + .el-tag {
     margin-left: 10px;
   }
   .button-new-tag {
     margin-left: 10px;
     height: 32px;
     line-height: 30px;
     padding-top: 0;
     padding-bottom: 0;
   }
   .input-new-tag {
     width: 90px;
     margin-left: 10px;
     vertical-align: bottom;
   }
   .demo-input-suffix{
     width: 20%;
     display: inline-block;
   }
   .demo-input-suffix>.el-input{
     width: 80%;
   }
   .time{
     width: 15%;
   }
   .tab{
     margin-top: 20px;
     margin-bottom: 40px;
   }
   .function{
     padding: 20px 0;
   }
   .cancel{
     margin-left: 265px !important;
   }
   </style>
