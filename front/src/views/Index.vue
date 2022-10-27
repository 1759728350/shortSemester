<template>
  <div class="index">
    <el-container class="body">
      <el-header class="header" height="40px">
        <div class="headerLeft">
          <div class="tabText">
            <i class="el-icon-sunrise-1"></i>诚挚租车首页
          </div>
          <div class="tab">
            <router-link to="#" class="a"
              >App下载</i
            ></router-link>
          </div>
          <div class="Loc">
            当前所在地:<span class="blue">&nbsp;{{ this.myprovince }}&nbsp;</span>
            <el-dropdown trigger="click" @command="changeProvice" placement="top"
              ><router-link to="#" class="a">[点击切换] </router-link>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                  v-for="item in cityList"
                  :command="item.province"
                  >{{ item.province }}</el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
        <div class="headerRight">
          <template v-if="!isLogin">
            <div class="tab">
              <router-link to="login" class="a">登录账号</router-link>
            </div>
            <div class="tab">
              <router-link to="/register" class="a">注册账号</router-link>
            </div>
          </template>
          <template v-if="isLogin">
            <div class="tab a">
              欢迎您!{{ userName == null ? "新用户" : userName }}
            </div>
          </template>
          <div class="tab">
            <router-link to="/adminLogin" class="a">管理员模式</router-link>
          </div>
          <div class="tab">
            <el-dropdown @command="handleUser" >
              <span class="el-dropdown-link a">
                个人中心<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown" lass="dropdown" style="top:0">
                <div>
                  <el-dropdown-item command="1">个人信息</el-dropdown-item>
                </div>
                <div>
                  <el-dropdown-item command="2">我的爱车</el-dropdown-item>
                </div>
                <div>
                  <el-dropdown-item command="3">我的租借</el-dropdown-item>
                </div>
                <div>
                  <el-dropdown-item command="0">退出登录</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div class="tab">
            <el-dropdown @command="handleVip">
              <span class="el-dropdown-link a">
                VIP中心<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown" class="dropdown">
                <div>
                  <el-dropdown-item command="1">我的VIP</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <hr />
      <el-container>
        <el-aside class="aside">
          <el-col class="el-menu-vertical-demo">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="rgb(245,245,245)"
            >
              <div class="block">
                <el-carousel height="150px" indicator-position="outside">
                  <el-carousel-item v-for="item in blockImg" :key="item.id">
                    <el-image
                      :src="item.url"
                      style="width: 100%; height: 100%"
                    ></el-image>
                  </el-carousel-item>
                </el-carousel>
              </div>
              <div class="companyRank">
                <h2 class="orange">{{ location }}热门车管公司</h2>
                <el-empty
                  description="没有找到相关信息"
                  v-if="isShow"
                ></el-empty>
                <el-menu-item
                  v-if="!isShow"
                  v-for="(item, index) in companyList.slice(
                    (this.currentPage - 1) * 4,
                    this.currentPage * 4
                  )"
                  :key="item.id"
                  @click="selectCompany(item.id)"
                >
                  <div class="rank">
                    <div class="orange">{{ index + 1 }}</div>
                  </div>
                  <div class="img">
                    <img
                      src="https://pic5.58cdn.com.cn/p1/small/n_v2e4834d1e2fdf4d34ac539e858b16a0e2.jpg?w=160&h=120&ss=1"
                      alt="图片"
                    />
                  </div>
                  <div class="imgText">
                    <div>{{ item.carCname }}</div>
                    <div class="gray">{{ location }}</div>
                  </div>
                </el-menu-item>
                <div class="changePage">
                  <el-pagination
                    background
                    small
                    layout="prev, pager, next"
                    @current-change="handleCurrentChange"
                    :page-size="4"
                    :current-page="currentPage"
                    :total="this.companyList.length"
                    :pager-count="5"
                  >
                  </el-pagination>
                </div>
              </div>
            </el-menu>
            <h2></h2>
          </el-col>
        </el-aside>
        <el-main class="main">
          <div class="search">
            <div class="logo">
              <el-image
                src="https://img0.baidu.com/it/u=927934986,287206218&fm=253&fmt=auto&app=138&f=JPEG?w=890&h=500"
                style="width: 100%; height: 100%"
                fit="cover"
              ></el-image>
            </div>
            <div class="find">
              <el-input
                placeholder="按照类型查找汽车"
                v-model="find"
                class="findInput"
              >
                <el-button
                  slot="append"
                  icon="el-icon-search"
                  type="primary"
                  class="findButton"
                  @click="search()"
                  >搜索</el-button
                >
              </el-input>
            </div>
          </div>
          <div class="breadcrumb">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>租借服务</el-breadcrumb-item>
              <el-breadcrumb-item>汽车租借</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="tableTab">
            <div class="oneTab">
              <span>汽车所属类型:&emsp;</span>
              <el-radio-group v-model="carType" @change="changeType()">
                <el-radio label="people"> <span> 个人</span></el-radio>
                <el-radio v-model="carType" label="carCompany">
                  <span>汽车中介公司</span>
                </el-radio>
              </el-radio-group>
            </div>
            <div class="twoTab">
              <span>服务区域:&emsp;</span>
              <el-radio-group v-model="location" @change="changeCity()">
                <el-radio-button
                  :label="city"
                  v-for="city in mycity"
                ></el-radio-button>
              </el-radio-group>
            </div>
          </div>
          <div class="table">
            <el-empty
              description="没有找到相关车辆信息"
              v-if="showCar||this.showData.length==0"
            ></el-empty>
            <div class="card" v-for="card in showData" v-if="!showCar">
              <div
                class="leftImg">
                <el-image
      style="width: 100%; height: 100%"
      :src="card.carImg"
      :fit="contain"></el-image>

              </div>
              <div class="rightText">
                <div class="Text">
                  <h1>{{ card.carInfo }}</h1>
                  <div>{{ card.carModel }}</div>
                  <div>
                    <el-tag>租借金额{{ card.leaseAmount }}元起</el-tag>
                    &emsp;
                    <el-tag>已行驶{{ card.mileage }}公里</el-tag>
                    &emsp;
                    <el-tag>{{ card.carBrand }}品牌</el-tag>
                  </div>
                </div>
                <div class="operate">
                  <el-button type="primary" @click="detail(card)"
                    >详情信息</el-button
                  >
                  <el-button type="info" @click="connect()">联系商家</el-button>
                  <el-button type="danger" @click="zujie(card)">租借汽车</el-button>
                </div>
              </div>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
    <el-dialog title="用户个人信息" :visible.sync="userDialog">
      <el-form :model="form" label-position="left">
        <el-form-item label="用户名称" label-width="90px">
          <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户邮箱" label-width="90px">
          <el-input v-model="form.userEmail"></el-input>
        </el-form-item>
        <el-form-item label="用户手机" label-width="90px">
          <el-input v-model="form.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="用户所在地" label-width="90px">
          <el-select v-model="form.userLoc" placeholder="请选择区域">
            <el-option
              :label="loc"
              :value="loc"
              v-for="loc in mycity"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userDialog = false">取 消</el-button>
        <el-button type="primary" @click="putUser()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="VIP办理"
      :visible.sync="getVip"
      width="30%"
    >
    <div>我的VIP:&nbsp;
  <el-radio v-model="vipLevel" label="0">普通会员</el-radio>
  <el-radio v-model="vipLevel" label="1">VIP(18元/月)</el-radio>
  <el-radio v-model="vipLevel" label="2">SVIP(30元/月)</el-radio>
   </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="getVip = false">取 消</el-button>
        <el-button type="primary" @click="updateVIP()"
          >办 理</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "index",
  data() {
    return {
      userName: "",
      userId: "",
      city: [],
      //main中展示汽车的boolean
      showCar: false,
      isLogin: false,
      userDialog: false,
      getVip: false,
      myLoc: "",
      vipLevel: "",
      form: {
        userId: "",
        userName: "",
        userEmail: "",
        userLoc: "",
        userPhone: "",
      },
      //默认服务区域
      example:
        "https://dss0.bdstatic.com/-0U0bnSm1A5BphGlnYG/tam-ogel/-912671055_-1201020382_360_212.png",
      location: "",
      carType: "people",
      currentPage: 1,
      find: "",
      showData: [],
      myprovince: "北京",
      mycity: [],
      isShow: false,
      companyList: [],
      carList: [],
      cityList: [
        { province: "北京", city: ["北京市"] },
        { province: "天津", city: ["天津市"] },
        {
          province: "河北省",
          city: [
            "石家庄市",
            "唐山市",
            "秦皇岛市",
            "邯郸市",
            "邢台市",
            "保定市",
            "张家口市",
            "承德市",
            "沧州市",
            "廊坊市",
            "衡水市",
          ],
        },
        {
          province: "山西省",
          city: [
            "太原市",
            "大同市",
            "阳泉市",
            "长治市",
            "晋城市",
            "朔州市",
            "晋中市",
            "运城市",
            "忻州市",
            "临汾市",
            "吕梁市",
          ],
        },
        {
          province: "内蒙古自治区",
          city: [
            "呼和浩特市",
            "包头市",
            "乌海市",
            "赤峰市",
            "通辽市",
            "鄂尔多斯市",
            "呼伦贝尔市",
            "巴彦淖尔市",
            "乌兰察布市",
            "兴安盟",
            "锡林郭勒盟",
            "阿拉善盟",
          ],
        },
        {
          province: "辽宁省",
          city: [
            "沈阳市",
            "大连市",
            "鞍山市",
            "抚顺市",
            "本溪市",
            "丹东市",
            "锦州市",
            "营口市",
            "阜新市",
            "辽阳市",
            "铁岭市",
            "盘锦市",
            "朝阳市",
            "葫芦岛市",
          ],
        },
        {
          province: "吉林省",
          city: [
            "长春市",
            "吉林市",
            "四平市",
            "辽源市",
            "通化市",
            "白山市",
            "松原市",
            "白城市",
            "延边",
          ],
        },
        {
          province: "黑龙江省",
          city: [
            "哈尔滨市",
            "齐齐哈尔市",
            "鸡西市",
            "鹤岗市",
            "双鸭山市",
            "大庆市",
            "伊春市",
            "佳木斯市",
            "七台河市",
            "牡丹江市",
            "黑河市",
            "绥化市",
            "大兴安岭地区",
          ],
        },
        { province: "上海", city: ["上海市"] },
        {
          province: "江苏省",
          city: [
            "南京市",
            "无锡市",
            "徐州市",
            "常州市",
            "苏州市",
            "南通市",
            "连云港市",
            "淮安市",
            "盐城市",
            "扬州市",
            "镇江市",
            "泰州市",
            "宿迁市",
          ],
        },
        {
          province: "浙江省",
          city: [
            "杭州市",
            "宁波市",
            "温州市",
            "嘉兴市",
            "湖州市",
            "绍兴市",
            "金华市",
            "衢州市",
            "舟山市",
            "台州市",
            "丽水市",
          ],
        },
        {
          province: "安徽省",
          city: [
            "合肥市",
            "芜湖市",
            "蚌埠市",
            "淮南市",
            "马鞍山市",
            "淮北市",
            "铜陵市",
            "安庆市",
            "黄山市",
            "滁州市",
            "阜阳市",
            "宿州市",
            "六安市",
            "亳州市",
            "池州市",
            "宣城市",
          ],
        },
        {
          province: "福建省",
          city: [
            "福州市",
            "厦门市",
            "莆田市",
            "三明市",
            "泉州市",
            "漳州市",
            "南平市",
            "龙岩市",
            "宁德市",
          ],
        },
        {
          province: "江西省",
          city: [
            "南昌市",
            "景德镇市",
            "萍乡市",
            "九江市",
            "新余市",
            "鹰潭市",
            "赣州市",
            "吉安市",
            "宜春市",
            "抚州市",
            "上饶市",
          ],
        },
        {
          province: "山东省",
          city: [
            "济南市",
            "青岛市",
            "淄博市",
            "枣庄市",
            "东营市",
            "烟台市",
            "潍坊市",
            "济宁市",
            "泰安市",
            "威海市",
            "日照市",
            "莱芜市",
            "临沂市",
            "德州市",
            "聊城市",
            "滨州市",
            "菏泽市",
          ],
        },
        {
          province: "河南省",
          city: [
            "郑州市",
            "开封市",
            "洛阳市",
            "平顶山市",
            "安阳市",
            "鹤壁市",
            "新乡市",
            "焦作市",
            "济源市",
            "濮阳市",
            "许昌市",
            "漯河市",
            "三门峡市",
            "南阳市",
            "商丘市",
            "信阳市",
            "周口市",
            "驻马店市",
          ],
        },
        {
          province: "湖北省",
          city: [
            "武汉市",
            "黄石市",
            "十堰市",
            "宜昌市",
            "襄阳市",
            "鄂州市",
            "荆门市",
            "孝感市",
            "荆州市",
            "黄冈市",
            "咸宁市",
            "随州市",
            "恩施",
            "仙桃市",
            "潜江市",
            "天门市",
            "神农架林区",
          ],
        },
        {
          province: "湖南省",
          city: [
            "长沙市",
            "株洲市",
            "湘潭市",
            "衡阳市",
            "邵阳市",
            "岳阳市",
            "常德市",
            "张家界市",
            "益阳市",
            "郴州市",
            "永州市",
            "怀化市",
            "娄底市",
            "湘西",
          ],
        },
        {
          province: "广东省",
          city: [
            "广州市",
            "深圳市",
            "珠海市",
            "汕头市",
            "佛山市",
            "江门市",
            "湛江市",
            "茂名市",
            "肇庆市",
            "惠州市",
            "梅州市",
            "汕尾市",
            "河源市",
            "阳江市",
            "清远市",
            "东莞市",
            "中山市",
            "潮州市",
          ],
        },
        {
          province: "广西壮族自治区",
          city: [
            "南宁市",
            "柳州市",
            "桂林市",
            "梧州市",
            "北海市",
            "防城港市",
            "钦州市",
            "贵港市",
            "玉林市",
            "百色市",
            "贺州市",
            "河池市",
            "来宾市",
            "崇左市",
          ],
        },
        {
          province: "海南省",
          city: [
            "海口市",
            "三亚市",
            "三沙市",
            "五指山市",
            "琼海市",
            "儋州市",
            "文昌市",
            "万宁市",
            "东方市",
            "定安县",
            "屯昌县",
            "澄迈县",
            "临高县",
            "白沙",
            "昌江",
            "乐东",
            "陵水",
            "保亭",
            "琼中",
          ],
        },
        { province: "重庆", city: ["重庆市"] },
        {
          province: "四川省",
          city: [
            "成都市",
            "自贡市",
            "攀枝花市",
            "泸州市",
            "德阳市",
            "绵阳市",
            "广元市",
            "遂宁市",
            "内江市",
            "乐山市",
            "眉山市",
            "宜宾市",
            "广安市",
            "达州市",
            "雅安市",
            "巴中市",
            "资阳市",
          ],
        },
        {
          province: "贵州省",
          city: [
            "贵阳市",
            "六盘水市",
            "遵义市",
            "安顺市",
            "铜仁市",
            "黔西南",
            "毕节市",
            "黔东南",
            "黔南",
          ],
        },
        {
          province: "云南省",
          city: [
            "昆明市",
            "曲靖市",
            "玉溪市",
            "保山市",
            "昭通市",
            "丽江市",
            "普洱市",
            "临沧市",
            "楚雄",
            "红河",
            "文山",
            "西双版纳",
            "大理",
            "德宏",
            "怒江",
            "迪庆",
          ],
        },
        {
          province: "西藏自治区",
          city: [
            "拉萨市",
            "昌都地区",
            "山南地区",
            "日喀则地区",
            "那曲地区",
            "阿里地区",
            "林芝地区",
          ],
        },
        {
          province: "陕西省",
          city: [
            "西安市",
            "铜川市",
            "宝鸡市",
            "咸阳市",
            "渭南市",
            "延安市",
            "汉中市",
            "榆林市",
            "安康市",
            "商洛市",
          ],
        },
        {
          province: "甘肃省",
          city: [
            "兰州市",
            "嘉峪关市",
            "金昌市",
            "白银市",
            "天水市",
            "武威市",
            "张掖市",
            "平凉市",
            "酒泉市",
            "庆阳市",
            "定西市",
            "陇南市",
            "临夏",
            "甘南",
          ],
        },
        {
          province: "青海省",
          city: [
            "西宁市",
            "海东市",
            "海北",
            "黄南",
            "海南",
            "果洛",
            "玉树",
            "海西",
          ],
        },
        {
          province: "宁夏回族自治区",
          city: ["银川市", "石嘴山市", "吴忠市", "固原市", "中卫市"],
        },
        {
          province: "新疆维吾尔自治区",
          city: [
            "乌鲁木齐市",
            "克拉玛依市",
            "吐鲁番地区",
            "哈密地区",
            "昌吉",
            "博尔塔拉",
            "巴音郭楞",
            "阿克苏地区",
            "克孜勒苏柯尔克孜自治州",
            "喀什地区",
            "和田地区",
            "伊犁",
            "塔城地区",
            "阿勒泰地区",
          ],
        },
        {
          province: "台湾",
          city: [
            "台北市",
            "高雄市",
            "台南市",
            "台中市",
            "金门县",
            "南投县",
            "基隆市",
            "新竹市",
            "嘉义市",
            "新北市",
            "宜兰县",
            "新竹县",
            "彰化县",
            "台东县",
            "花莲县",
            "澎湖县",
            "连江县",
          ],
        },
        { province: "香港特别行政区", city: ["香港岛", "新界"] },
        { province: "澳门特别行政区", city: ["澳门", "离岛"] },
      ],
      //轮播图
      blockImg: [
        {
          id: "1",
          url: "https://pic1.58cdn.com.cn/p1/big/n_v29a5fa4945a0849738f9ee2943f5f5c97.jpg?w=330&h=248",
        },
        {
          id: "2",
          url: "https://pic5.58cdn.com.cn/p1/big/n_v249f06bbb83c54e6d8e6805030140cc18.jpg?w=330&h=248",
        },
        {
          id: "3",
          url: "https://pic1.58cdn.com.cn/pic/big/n_v230b233c1acc147bcbdb0eee131d3713e.jpg?w=338&h=253",
        },
        {
          id: "4",
          url: "https://pic1.58cdn.com.cn/p1/big/n_v246fe3c13086845989601bca68da39c68.png?w=294&h=220",
        },
        {
          id: "5",
          url: "https://pic6.58cdn.com.cn/p1/big/n_v210d1957ddbaa439d9e45a899ef3f0a85.png?w=294&h=220",
        },
      ],
    };
  },
  mounted() {
    this.getUserInfo();
    this.getMyCity();
  },
  methods: {
    //租借
    zujie(car) {
      this.$message.warning("请确认车辆信息");
      if (this.loginFilter()) {
        this.loginJump();
      } else {
        let mycar = JSON.stringify(car);
        this.$router.push({
          path: "/detail",
          query: {
            thisCar: mycar,
          },
        });
      }
    },
    //跳转到我的爱车
    GotoMyCar() {
      if (this.loginFilter()) {
        this.loginJump();
      } else {
        this.$router.push({
          path: "/myCar",
        });
      }
    },
    //点击联系方式
    connect() {
      this.$notify({
        title: "联系方式",
        message: "电话:15042070108",
      });
    },
    //升级vip
    updateVIP() {
      axios
        .put("http://localhost:8000/user/updateVipLevel", {
          vipId: this.vipLevel,
          userId: this.userId,
        })
        .then((res) => {
          this.getUser();
          this.$message.success("受理成功!");
        })
        .catch((err) => {
          this.$message.error("受理失败!,请检查余额");
        });
      this.getVip = false;
    },
    //详情动态路由跳转
    detail(car) {
      if (this.loginFilter()) {
        this.loginJump();
      } else {
        let mycar = JSON.stringify(car);
        this.$router.push({
          path: "/detail",
          query: {
            thisCar: mycar,
          },
        });
      }
    },

    //vip下拉菜单
    handleVip(command) {
      if (this.loginFilter()) {
        this.loginJump();
      } else {
        if (command == 1) {
          this.vipLevel =
            JSON.parse(localStorage.user).vipId == null
              ? "0"
              : JSON.parse(localStorage.user).vipId;
          this.getVip = true;
        }
      }
    },
    //修改用户信息
    putUser() {
      axios
        .put(`http://localhost:8000/user/updateInfo`, this.form)
        .then((res) => {
          this.getUser();
          this.$message.success("修改成功!");
        })
        .catch((err) => {
          console.log(err);
        });
      this.userDialog = false;
    },
    //axios请求用户信息并替换掉本地存储
    getUser() {
      let accout = JSON.parse(localStorage.user).userAccount;
      let pwd = JSON.parse(localStorage.user).userPassword;
      axios
        .post("http://localhost:8000/user/login", {
          userAccount: accout,
          userPassword: pwd,
        })
        .then((res) => {
          localStorage.user = JSON.stringify(res.data.data);
          this.getUserInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //左侧导航栏切换
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    //左侧导航栏关闭
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //修改城市
    changeCity() {
      this.getCarAndCarCompanyInfo();
    },
    //切换汽车所属类型
    changeType() {
      this.getCarAndCarCompanyInfo();
    },
    //获取当前城市所有租借车辆和汽车公司信息
    getCarAndCarCompanyInfo() {
      //获取汽车公司信息
      localStorage.setItem("myloc", this.location);
      axios
        .get(`http://localhost:8000/company/city?myCity=${this.location}`)
        .then((res) => {
          if (res.data.data.length != 0) {
            this.companyList = res.data.data;
            this.isShow = false;
          } else {
            //如果没数据
            this.isShow = true;
          }
        })
        .catch((err) => {
          console.log(err);
        });

      //获取租借汽车信息
      let type = this.carType == "people" ? 0 : 1;
      axios
        .get(
          `http://localhost:8000/car/cityAndCarType?myCity=${this.location}&carType=${type}`
        )
        .then((res) => {
          if (res.data.data.length == 0) {
            this.showCar = true;
          } else {
            this.carList = res.data.data;
            this.carList = this.carList.filter((item) => {
              return item.state == 1 && item.userId != this.userId;
            });
            this.showData = this.carList;
            this.showCar = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      console.log(`当前页: ${val}`);
    },
    //通过省默认获得第一个市信息(包括汽车和汽车公司信息)
    getMyCity() {
      this.cityList.forEach((city) => {
        if (city.province === this.myprovince) {
          this.mycity = city.city;
        }
        this.location = this.mycity[0];
      });

      this.getCarAndCarCompanyInfo();
    },
    //用户下拉
    handleUser(command) {
      if (command == 0) {
        this.quitLogin();
      }
      if (command == 1) {
        this.changeUserInfo();
      }
      if (command == 2) {
        this.GotoMyCar();
      }
      if (command == 3) {
        if (!this.loginFilter()) {
          this.$router.push({
            path: "/myRent",
          });
        } else {
          this.loginJump();
        }
      }
    },
    //获取用户信息
    getUserInfo() {
      let temp = localStorage.user;
      if (temp != null && temp != undefined) {
        this.userId = JSON.parse(localStorage.user).userId;
        this.userName = JSON.parse(localStorage.user).userName;
        this.isLogin = true;
      } else {
        this.isLogin = false;
      }
    },
    //切换省份
    changeProvice(command) {
      this.myprovince = command;
      this.getMyCity();
    },
    //查找方法
    search() {
      if (this.find != "" && this.find.length != 0) {
        console.log(this.carList);
        this.showData = this.carList;
        this.showData = this.showData.filter((item) => {
          return item.carInfo.indexOf(this.find) != -1;
        });
      } else {
        this.getCarAndCarCompanyInfo();
      }
    },
    //退出登录
    quitLogin() {
      (this.userId = ""), (this.userName = ""), localStorage.clear();
      this.isLogin = false;
      this.$message.warning("已登出");
      this.getMyCity();
    },
    //用户修改个人信息
    changeUserInfo() {
      if (this.loginFilter()) {
        this.loginJump();
      } else {
        this.userDialog = true;
        let user = JSON.parse(localStorage.user);
        this.form.userId = user.userId;
        this.form.userName = user.userName;
        this.form.userEmail = user.userEmail;
        this.form.userPhone = user.userPhone;
        this.form.userLoc = user.userLoc;
      }
    },
    //登录拦截器
    loginFilter() {
      if (this.userId.length != 0) {
        return false;
      } else {
        return true;
      }
    },
    //登录路由跳转
    loginJump() {
      this.$message.error("您还未登录,请先登录");
      this.$router.push({
        path: "/login",
      });
    },
  },
};
</script>

<style scoped>
.body {
  width: 100%;
  height: 850px;
}
.header {
  display: flex;
  justify-content: space-between;
  align-content: center;
  padding: 0;
}
.headerLeft {
  width: 35%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-content: center;
}
.headerRight {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-content: center;
}
.tab {
  width: 15%;
  height: 60%;
  position: relative;
  top: 20%;
}
.a {
  text-decoration: none;
  font-size: 16px;
  color: black;
  font-weight: bolder;
}
.a:hover {
  color: #409eff;
}
.tabText {
  font-size: 20px;
  width: 30%;
  height: 60%;
  font-weight: bolder;
  position: relative;
  top: 20%;
  color: #409eff;
}
.Loc {
  width: 50%;
  height: 60%;
  font-weight: bolder;
  position: relative;
  top: 20%;
}
.blue {
  color: #409eff;
}
.el-menu-vertical-demo {
  width: 100%;
  height: 100%;
  text-align: center;
}
.orange {
  color: orangered;
}
.companyRank h2 {
  margin-top: 4%;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 4%;
}
.el-menu-item {
  width: 100%;
  height: 15%;
  border-bottom: 1px solid rgba(17, 17, 17, 0.15);
  margin-bottom: 5px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
.el-carousel__item:nth-child(n) {
  background-color: #99a9bf;
}
.img {
  float: left;
  width: 45%;
  height: 100%;
}
.img > img {
  width: 100%;
  height: 100%;
}
.imgText {
  width: 45%;
  height: 100%;
  font-size: 16px;
  font-weight: bold;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  align-content: flex-start;
}
.imgText > div {
  width: 100%;
  height: 50%;
  text-align: left;
}
.gray {
  color: gray;
}
.rank {
  float: left;
  height: 100%;
  width: 10%;
}
.rank > div {
  font-weight: bolder;
  font-size: 20px;
}
.block {
  width: 100%;
  height: 22%;
}
.companyRank {
  width: 100%;
  height: 75%;
}
.changePage {
  margin-top: 5%;
  width: 100%;
  height: 8%;
}
.search {
  width: 100%;
  height: 18%;
  /* background-color: royalblue; */
}
.breadcrumb {
  margin-top: 7px;
  width: 100%;
  height: 5%;
}
.logo {
  width: 30%;
  height: 100%;
  float: left;
}
.find {
  float: right;
  width: 60%;
  position: relative;
  top: 30%;
  right: 5%;
}
::v-deep .el-input__inner {
  border: 2px solid #409eff;
  height: 50px;
  font-size: 18px;
  font-weight: bold;
}
.findButton {
  font-size: 18px;
  font-weight: bold;
  height: 50px;
}
.findButton:hover {
  color: whitesmoke;
  background-color: #409eff;
}
.tableTab {
  width: 100%;
  height: 15%;
}
.oneTab {
  width: 100%;
  height: 50%;
}
.twoTab {
  width: 100%;
  height: 50%;
}
.table {
  width: 100%;
  height: 61%;
  overflow: auto;
}
.oneTab span,
.twoTab span {
  font-weight: bold;
  font-size: 18px;
}
::v-deep .el-radio-button__inner {
  font-size: 12px;
  font-weight: bold;
}
.card {
  width: 100%;
  height: 35%;
  margin-bottom: 0.5%;
  display: flex;
  background-color: rgb(245, 245, 245);
}
.card:hover {
  background-color: rgba(204, 204, 204, 0.8);
}
.leftImg {
  width: 25%;
  height: 100%;
  /* background-size: 100% 100%; */
}
.rightText {
  width: 75%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-content: center;
}
.Text {
  width: 70%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-content: space-around;
  flex-wrap: wrap;
}
.operate {
  width: 30%;
  height: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  flex-wrap: wrap;
}
.Text > h1 {
  width: 75%;
  height: 30%;
}
.Text > div {
  width: 75%;
  height: 18%;
}
</style>