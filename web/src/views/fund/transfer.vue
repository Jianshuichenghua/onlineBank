<template>
  <div class="slideYz">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="Account">
        <el-autocomplete
            v-model="form.account"
            :fetch-suggestions="findByAccountLike"
            placeholder="Please enter content"
            >
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="Target">
        <el-autocomplete
            v-model="form.targetAccount"
            :fetch-suggestions="findByAccountLike"
            placeholder="Please enter content"
            >
        </el-autocomplete>
      </el-form-item>
      <el-form-item label="Amount">
        <el-input placeholder="Please enter content" v-model="form.amount">
        </el-input>
      </el-form-item>
      <el-form-item label="Description">
        <el-input
        type="textarea"
        :rows="3"
        placeholder="Please enter content"
        v-model="form.description">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="transfer">transfer</el-button>
      </el-form-item>
      <el-dialog
        title="Password Confirm"
        :visible.sync="passwrodConfirmVisible"
        width="30%"
        :before-close="passwrodConfirmClose">
        <el-form-item label="Password: ">
            <el-input placeholder="Please enter the password" v-model="form.password" type="password"/>
        </el-form-item>
        <span slot="footer" class="dialog-footer">
            <el-button @click="passwrodConfirmVisible = false;form.password = ''">Cancel</el-button>
            <el-button type="primary" @click="submit">Confirm</el-button>
        </span>
    </el-dialog>
    </el-form>
  </div>
</template>

<script>
import { transfer } from '@/api/fund'
import { findByAccountLike} from '@/api/account'

export default {
  data() {
    return {
      form:{
        account:'',
        targetAccount:'',
        amount:0,
        description:'',
        password:''
      },
      passwrodConfirmVisible:false
    }
  },
  methods: {
    findByAccountLike(account, cb) {
        const self = this;
        const data = {
          "account": account
        }
        findByAccountLike(data)
        .then(res => {
            const accounts = res;
            const result = self.convertAccounts(accounts);
            cb(result);
        })
        .catch(error => {
            this.$message.error(error);
        })
    },
    convertAccounts(accounts) {
        return accounts.map(account => {
          return {"value": account.account};
        });
    },
    transfer() {
        const self = this;
        self.passwrodConfirmVisible = true;
    },
    passwrodConfirmClose() {
        const self = this;
        self.form.password = '';
    },
    submit() {
        const self = this;
        const form = self.form;
        transfer(self.form)
        .then(res => {
            const result = res;
            self.passwrodConfirmVisible = false;
            this.$message.success(`transfer successfully! balance: ${result[form.account]}`);
        })
        .catch(error => {
            this.$message.error(error.msg);
        })
    }
  }
}
</script>
<style lang="scss" scoped>
.slideSty {
  position: absolute;
  width: 380px;
  height: 311px;
  background: #e8e8e8;
  border: 1px solid #dcdcdc;
  left: 50%;
  top: 200px;
  margin-left: -188px;
  z-index: 99;
  border-radius: 5px;
}
.iconBtn {
  padding: 9px 0 0 19px;
  color: #5f5f5f;
  border-top: 1px solid #d8d8d8;
  margin-top: 17px;
  i {
    font-size: 22px;
    cursor: pointer;
  }
  i:last-child {
    margin-left: 7px;
  }
}
</style>
<style>
.slideSty .slide-verify {
  margin: 13px auto 0 auto;
  width: 350px !important;
}
.slideSty .slide-verify-slider {
  width: 100% !important;
}
.slideSty .slide-verify-refresh-icon {
  display: none;
}
</style>
