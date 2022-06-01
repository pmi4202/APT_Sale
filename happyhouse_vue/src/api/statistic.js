import { apiInstance } from "./index.js";

const api = apiInstance();

function likeTop10(code, success, fail) {
  api.get(`/statistic/liketop10/${code}`).then(success).catch(fail);
}

function dealTop10(code, success, fail) {
  api.get(`/statistic/dealtop10/${code}`).then(success).catch(fail);
}
function dealCount(success, fail) {
  api.get("/statistic/dealcount").then(success).catch(fail);
}

function getDustInfo(siName, success, fail) {
  api.get(`/statistic/dust/${siName}`).then(success).catch(fail);
}
export { likeTop10, dealTop10, dealCount, getDustInfo };
