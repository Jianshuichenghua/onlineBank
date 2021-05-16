<template>
  <div>
    <p class="fund-header">Funds Transfer Transactions</p>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="id" label="Transaction ID" width="180">
      </el-table-column>
      <el-table-column prop="amount" label="Amount(USD)" width="180">
      </el-table-column>
      <el-table-column prop="targetAccount" label="Recopient Name">
      </el-table-column>
      <el-table-column prop="sourceAccount" label="Account #">
      </el-table-column>
      <el-table-column prop="createDate" label="Date"> </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      background
      :page-size="pageSize"
      layout="prev, pager, next"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
import { getFunds } from "@/api/fund";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      tableData: [],
      pageNo: 1,
      pageSize: 10,
      total: 0,
    };
  },
  computed: {
    ...mapGetters(["userName"]),
  },
  created() {
    const { pageNo, pageSize } = this;
    this.getFunds(pageNo, pageSize);
  },

  methods: {
    getFunds(pageNo, pageSize) {
      const { userName } = this;
      const account = userName;
      getFunds({ account, pageNo, pageSize }).then((res) => {
        this.tableData = res.result;
        this.total = res.totalCount;
      });
    },
    handleCurrentChange(pageNo) {
      this.getFunds(pageNo, this.pageSize);
    },
  },
};
</script>
<style>
.fund-header {
  font-size: 30px;
  color: #48aee9;
}
</style>