<template>
  <div class="body2">
    <el-card class="box-card">
      <div class="clearfix">
        <h2 class="search">
          用户评论列表<el-input
            v-model="search"
            placeholder="请输入您要查找的评论信息"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="find()"
            ></el-button
          ></el-input>
        </h2>
      </div>
      <el-table :data="showData" stripe style="width: 100%">
        <el-table-column
          prop="coId"
          label="评论编号"
          width="180"
        ></el-table-column>
        <el-table-column prop="carId" label="评论汽车id"> </el-table-column>
        <el-table-column prop="coUid" label="评论用户id">
        </el-table-column>
        <el-table-column label="评论信息">
          <template slot-scope="scope">
            <el-popover
              placement="bottom"
              title="评论信息"
              width="300"
              trigger="click"
              :content="scope.row.content"
            >
              <el-button slot="reference">点击查看</el-button>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="carPhone" label="评价">
          <template slot-scope="scope">
            <el-rate v-model="scope.row.level*1" disabled></el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="carCInfo" label="评论时间">
            <template scope="scope">
                {{new Date(scope.row.createTime).toLocaleDateString()}}
            </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          @current-change="changeCurrent"  
          :current-page="currentPage"
          :page-sizes="[5]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.length"
          @next-click="next()"
          @prev-click="prev()"
        >
        </el-pagination>
      </div>
    </el-card>
   
     <el-dialog
      title="修改评论"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="100px" label-position="left">
        <el-form-item label="评论id">
          <el-input v-model="form.coId" disabled></el-input>
        </el-form-item>
        </el-form-item>
        <el-form-item label="评论汽车id">
          <el-input v-model="form.carId" disabled></el-input>
        </el-form-item>
         <el-form-item label="评论时间">
          <el-input v-model="new Date(form.createTime).toLocaleDateString()" disabled></el-input>
        </el-form-item>
        <el-form-item label="评论内容">
          <el-input v-model="form.content" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="评价等级">
          <el-rate v-model="form.level"></el-rate>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="close(2)">取 消</el-button>
        <el-button type="primary" @click="putData()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CarCompany",
  data() {
    return {
      search: "",
      dialogVisible2:false,
      length:0,
      showData: [],
      currentPage: 1,
      user: [],
      price: "",
      carCompany: [],
      carid:"",
      form: {
        carId:"",
        coId:"",
        coUid:"",
        content:"",
        createTime:"",
        level:"",
      },
    };
  },
  mounted() {
    this.getUser();
  },
  methods: {
    close(type){
      if(type==1){
        this.dialogVisible = false
      }else{
        this.dialogVisible2 = false
      }
      for(let i in this.form){
         this.form[i] = ""
        }
    },
    putData(){
        this.form.level = this.form.level*1; //转数字
        axios.put(`http://localhost:8000/commit/updateCommit`,this.form).then(res=>{
         this.getUser()
         this.dialogVisible2 = false
         for(let i in this.form){
              this.form[i] = ""
         }
          this.$message({
            message: "修改成功",
            type: "success",
          });
          this.currentPage = 1;
      }).catch(err=>{
           this.$message({
            message: "修改失败",
            type: "error",
          });
      })
    },
    changeCurrent(val){
      this.currentPage = val
      this.showData = this.user.slice((this.currentPage-1)*5,this.currentPage*5)
    },
    //获取所有评论信息
    getUser() {
      axios
        .get(`http://localhost:8000/commit/selectAllCommit`)
        .then((res) => {
          console.log(res.data)
          this.user = res.data.data;
          this.showData = this.user.slice(0,5)
          this.length = this.user.length
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleEdit(index, row) {
      this.dialogVisible2 = true
      for(let i in this.form){
        this.form[i] = row[i]
      }
    },
    //删除数据
    handleDelete(index, row) {
      axios.delete(`http://localhost:8000/commit/deleteCommit?commitId=${row.coId}`).then(res=>{
         this.getUser()
         this.currentPage = 1;
          this.$message({
            message: "删除成功",
            type: "success",
          });
      }).catch(ERR=>{
          this.$message({
            message: "删除失败",
            type: "error",
          });
      })
    },

    //分页触发
    next(){
      this.currentPage++
      this.showData = this.user.slice((this.currentPage-1)*5,this.currentPage*5)
    },
    //前一页
    prev(){
      this.currentPage--
      this.showData = this.user.slice((this.currentPage-1)*5,this.currentPage*5)
    },
    //关闭之前
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    //根据名字搜索
    find() {
      this.showData = this.user.filter(item=>{
        return item.content.indexOf(this.search)!=-1
      })
      this.length = this.showData.length
      if(this.showData.length>5){
        this.showData = this.showData.slice(0,5)
      }
    },
  },
};
</script>

<style scoped>
.owner {
  width: 70%;
}
.body2 {
  width: 100%;
  height: 100%;

}
.box-card {
  width: 100%;
  height: 100%;
}
.block {
  text-align: center;
}
</style>