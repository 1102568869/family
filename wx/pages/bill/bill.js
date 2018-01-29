import { formatTime } from '../../utils/util.js'
import apis from '../../utils/url.js'

import { ajaxGet, ajaxPost } from '../../utils/httpUtil.js'

const app = getApp()
// pages/bill/bill.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bill: {
      id: null,
      member: null,
      balance: 0,
      recordtime: formatTime(new Date())
    },
    types: Array,
    members: Array,
    balanceTypes: Array,
    memberIndex: 0,
    tags: Array,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getMembers();
    this.getTypes();
    this.getBalanceTypes();
    this.getTop10Tags();
    this.setData({ bill: { ...this.data.bill, id: options.id } });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (!this.data.bill.id) {
      return;
    }
    // while (true) {
    //   if (this.data.types && this.data.members && this.data.balanceTypes && this.data.tags) {
    //     break;
    //   }
    // }
    this.getBillItem(this.data.bill.id);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value)
    var vm = this;
    if (vm.isSubmitting) {
      wx.showToast({
        mask: true,
        title: '请勿重复提交!!!',
        icon: 'loading',
        duration: 1000
      })
      return false;
    }
    vm.isSubmitting = true;

    let form = e.detail.value;
    let tagIdsInt = [];
    for (var k in form.tagIds) {
      tagIdsInt.push(parseInt(form.tagIds[k]));
    }
    this.setData({
      bill:
      {
        ...this.data.bill,
        money: parseFloat(form.money),
        name: form.name,
        type: form.type,
        tagIds: tagIdsInt,
        comment: form.comment,
        member: this.data.bill.member || this.data.members[this.data.memberIndex].id,
      }
    });
    ajaxPost(this.data.bill.id ? apis._bill_post_update : apis._bill_post_add, this.data.bill, (result) => {
      if (result === true) {
        wx.showToast({
          mask: true,
          title: '提交成功!!!',
          icon: 'success',
          duration: 1000,
          success: function () {
            wx.redirectTo({
              url: '../billlist/billlist'
            })
          }
        })
      } else {
        wx.showToast({
          mask: true,
          title: result.message,
          icon: 'none',
          duration: 3000,
        })
        vm.isSubmitting = false;
      }
    }, (msg) => {
      wx.showToast({
        mask: true,
        title: '系统异常,请联系管理员!',
        icon: 'none',
        duration: 3000,
      })
      vm.isSubmitting = false;
    });
  },
  formReset: function () {
    console.log('form发生了reset事件')
  },
  getBillItem: function (id) {
    ajaxGet(apis._bill_get_item + '?id=' + id, (data) => {
      this.setData({ bill: data })
      for (var k in this.data.members) {
        if (this.data.members[k].id == this.data.bill.member) {
          this.setData({ memberIndex: k })
          console.info(k);
          break;
        }
      }
      var tags = this.data.tags;
      var tagsWithCheck = [];
      for (var k in tags) {
        var tag = tags[k];
        for (var j in this.data.bill.tagIds) {
          if (tag.id == this.data.bill.tagIds[j]) {
            tag.checked = true;
            break;
          }
        }
        tagsWithCheck.push(tag);
      }
      this.setData({ tags: tagsWithCheck });
    });
  },
  getTypes: function () {
    ajaxGet(apis._billtype_get_all, (data) => this.setData({ types: data }));
  },
  getMembers: function () {
    ajaxGet(apis._familymember_get_all, (data) => {
      this.setData({ members: data })
      if (this.data.bill.member == null) {
        return;
      }
      for (var k in this.data.members) {
        if (this.data.members[k].id == this.data.bill.member) {
          this.setData({ memberIndex: k })
          console.info(k);
          break;
        }
      }
    });
  },
  getBalanceTypes: function () {
    ajaxGet(apis._bill_get_balanceTypes, (data) => this.setData({ balanceTypes: data }));
  },
  getTop10Tags: function () {
    ajaxGet(apis._billtag_get_top10, (data) => {
      this.setData({ tags: data })
      if (this.data.bill.tagIds == null) {
        return;
      }
      var tags = this.data.tags;
      var tagsWithCheck = [];
      for (var k in tags) {
        var tag = tags[k];
        for (var j in this.data.bill.tagIds) {
          if (tag.id == this.data.bill.tagIds[j]) {
            tag.checked = true;
            break;
          }
        }
        tagsWithCheck.push(tag);
      }
      this.setData({ tags: tagsWithCheck });
    });
  },
  selectMember: function (event) {
    console.info(this.data.bill);
    let currentMemberIndex = event.detail.value;
    let bill = this.data.bill;
    bill.member = this.data.members[currentMemberIndex].id;
    this.setData({ bill: bill, memberIndex: currentMemberIndex });
    console.info(this.data.bill);
  },
  selectRecordTime: function (event) {
    this.setData({ bill: { ...this.data.bill, recordtime: event.detail.value } });
  },
  inputName: function (event) {
    this.setData({ bill: { ...this.data.bill, name: event.detail.value } });
  },
  inputMoney: function (event) {
    this.setData({ bill: { ...this.data.bill, money: event.detail.value } });
  },
  inputComment: function (event) {
    this.setData({ bill: { ...this.data.bill, comment: event.detail.value } });
  },
  changeType: function (event) {
    this.setData({ bill: { ...this.data.bill, type: event.detail.value } });
  },
})