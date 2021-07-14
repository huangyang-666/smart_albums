<template>
  <div class="class">
    <div id="top">
      <el-button  style="float: right; margin-left: 10px;margin-top: 15px" class="small" type="warning" size="large" @click="downBatch()">批量下载</el-button>
      <el-button style="float: right;  margin-top: 15px" class="small" type="warning" size="large" @click="removeBatch()">批量删除</el-button>
      <el-input v-model="keywords" autocomplete="off" size="large" style="width: 500px;margin-right: 10px;margin-top: 15px" placeholder="关键字搜索"></el-input>
      <el-button  style="margin-right: 20px" size="small" type="primary" icon="el-icon-search" @click="searchBooksByKeyWord()">搜索</el-button>
    </div>
    <el-table
      ref="multipleTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      tooltip-effect="dark"
      style="width: 54%;margin-left: 20%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        type="index"
        width="55">
      </el-table-column>
      <el-table-column
        prop="name"
        label="图片名称"
        width="300">
      </el-table-column>
      <el-table-column
        label="日期"
        width="100">
        <template slot-scope="scope">{{ scope.row.uploadTime }}</template>
      </el-table-column>
      <el-table-column label="类别"
                       width="100">
        <template slot-scope="scope">
                    <span v-if="scope.row.show">
                        <el-input size="mini" placeholder="请输入内容" v-model="scope.row.imgType"></el-input>
                    </span>
          <span v-else>{{scope.row.imgType}}</span>
        </template>
      </el-table-column>
      <el-table-column label="详细信息"
                       width="100">
        <template slot-scope="scope">
                    <span v-if="scope.row.show">
                        <el-input size="mini" placeholder="请输入内容" v-model="scope.row.details"></el-input>
                    </span>
          <span v-else>{{scope.row.details}}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片"
                       width="100">
        　　<template slot-scope="scope">
        <!--        　　　　<img :src="'../../../static/smart_photos/allimgs/'+scope.row.name" width="70" height="70" class="head_pic" :preview-src-list="['../../../static/smart_photos/allimgs/'+scope.row.name]"/>-->
        <el-image
          style="width: 80px; height: 80px"
          :src="'../../../static/smart_photos/allimgs/'+scope.row.name"
          :preview-src-list="['../../../static/smart_photos/allimgs/'+scope.row.name]"
        ></el-image>
        　　</template>
      </el-table-column>
      <el-table-column
        width="200">
        <template slot="header"
                  slot-scope="scope">
          操作
        </template>
        <template slot-scope="scope">
          <el-button
            id="bt"
            style="color: white"
            size="large"
            @click="handleEdit(scope.$index, scope.row)">{{scope.row.show ?'保存':"编辑"}}</el-button>
          <el-button
            size="large"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableData.length">
    </el-pagination>

  </div>
</template>

<script>
  import * as axios from 'axios'
  import Vue from 'vue'

  export default {
    inject: ['reload'],
    data() {
      return {
        tableData: {},
        multipleSelection:[],//多选的数据
        currentPage: 1,
        // 每页多少条
        pageSize: 10,
        search: '',
        arr:[],
        keywords:'',

      }
    },
    created() {
      this.param = new FormData();
      this.param.append('user',this.$store.getters.getUser.username);
      const _this = this;
      axios.post('http://localhost:8086/showallimgInfo',_this.param)
        .then(function (resp) {
          console.log(resp);
          _this.tableData = resp.data;
          _this.tableData.map(i => {
            i.show = false
            return i
          })
        })
    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleClick(name,row) {
        console.log(name,row);
      },
      handleEdit(index, row) {
        // console.log(index.name, row);
//              @click="handleEdit(scope.$index, scope.row)">{{scope.row.show?'保存':"编辑"}}</el-button>
        row.show = row.show ? false : true
        Vue.set(this.tableData, index, row)
        console.log("ok")
        if (!row.show) {
          this.param = new FormData();
          this.param.append('user', this.$store.getters.getUser.username);
          this.param.append('type', row.imgType);
          this.param.append('filename', row.name);
          this.param.append('details', row.details);
          const _this = this;
          axios.post('http://localhost:8086/updateImgInfos', _this.param,{
            headers:{
              authorization:localStorage.getItem('token')
            }
          }).then(function(resp) {

          });
        }
      },
      handleDelete(index,row) {
        console.log(row.name);
        console.log("ok")
        this.param = new FormData();
        this.param.append('user', this.$store.getters.getUser.username);
        this.param.append('filenames', row.name);
        const _this = this;
        axios.post('http://localhost:8086/deleteImgs',_this.param,{
          headers:{
            authorization:localStorage.getItem('token')
          }
        }).then(function (resp){
          console.log(resp)
          _this.reload()
        });

      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      downBatch () {
        const length = this.multipleSelection.length;
        // alert(length);
        for (let i = 0; i < length; i++) {
          this.names += this.multipleSelection[i].name+',';
          console.log(this.multipleSelection[i].name);
          console.log(this.$store.getters.getUser.username)
        }
        this.param = new FormData();
        this.param.append('filenames',this.names);
        this.param.append('user',this.$store.getters.getUser.username);
        console.log(this.param);
        const _this = this;
        console.log(_this.param);
        axios.post('http://localhost:8086/picdownload',_this.param,{responseType:'blob'})
          .then(res => {
            console.log("ok");
            let data = res.data;
            let url = window.URL.createObjectURL(new Blob([data]));
            let link = document.createElement('a');
            link.style.display = 'none';
            link.href = url;
            link.setAttribute('download', 'picdownload.rar');
            document.body.appendChild(link);
            link.click();
            _this.reload()
          }).catch((error) => {})
      },
      removeBatch () {
        const length = this.multipleSelection.length;
        // alert(length);
        for (let i = 0; i < length; i++) {
          this.names += this.multipleSelection[i].name+',';
          console.log(this.multipleSelection[i].name);
          console.log(this.$store.getters.getUser.username)
        }
        this.param = new FormData();
        this.param.append('filenames',this.names);
        this.param.append('user',this.$store.getters.getUser.username);
        console.log(this.param);
        const _this = this;
        console.log(_this.param);
        axios.post('http://localhost:8086/deleteImgs',_this.param,{
          headers:{
            authorization:localStorage.getItem('token')
          }
        }).then(function (resp){
          _this.reload()
        });
      },
      //搜索
      searchBooksByKeyWord() {
        this.param = new FormData();
        this.param.append('keywords',this.keywords);
        this.param.append('user',this.$store.getters.getUser.username);
        console.log(this.keywords);
        console.log(this.$store.getters.getUser.username);
        console.log("okokok");
        console.log(this.param);
        const _this = this;
        axios.post('http://localhost:8086/test',_this.param,{
          headers:{
            authorization:localStorage.getItem('token')
          }
        }).then(function (resp){
          console.log("okokok");
          console.log(resp);
          _this.tableData = resp.data;
        });
      },
      // 每页多少条
      handleSizeChange(val) {
        this.pageSize = val;
      },
      // 当前页
      handleCurrentChange(val) {
        this.currentPage = val;
      },
    }
  }
</script>
<style>
  #bt{
    background-color: #42b983;
  }
  #bt1{
    background-color: #1989fa;
  }
  .class{
    background-image: url(../../assets/beijing4.jpg);
    text-align: center;
  }

</style>
