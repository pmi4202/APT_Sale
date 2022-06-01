import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/bjd/sido`).then(success).catch(fail);
}
function gugunList(params, success, fail) {
  api.get(`/bjd/gugun/${params.sidocode}`).then(success).catch(fail);
}
function dongList(params, success, fail) {
  api.get(`/bjd/dong/${params.guguncode}`).then(success).catch(fail);
}
function houseList(params, success, fail) {
  api
    .get(`/house/${params.code}`, {
      headers: { "JWT-AUTHENTICATION": sessionStorage.getItem("access-token") },
    })
    .then(success)
    .catch(fail);
}
function houseDetail(params, success, fail) {
  api
    .get(`/house/detail/${params.aptCode}`, {
      headers: { "JWT-AUTHENTICATION": sessionStorage.getItem("access-token") },
    })
    .then(success)
    .catch(fail);
}
//좋아요
function like(params, success, fail) {
  api
    .post(`/house/like/${params.aptcode}`, null, {
      headers: { "JWT-AUTHENTICATION": sessionStorage.getItem("access-token") },
      params: params,
    })
    .then(success)
    .catch(fail);
}
function unlike(params, success, fail) {
  api
    .post(`/house/unlike/${params.aptcode}`, null, {
      headers: { "JWT-AUTHENTICATION": sessionStorage.getItem("access-token") },
      params: params,
    })
    .then(success)
    .catch(fail);
}
function likeListOfUser(success, fail) {
  api
    .get(`/house/like`, {
      headers: { "JWT-AUTHENTICATION": sessionStorage.getItem("access-token") },
    })
    .then(success)
    .catch(fail);
}
function marketList(params, success, fail) {
  api
    .get(`/market/${params.dongCode}/${params.categorycode}`)
    .then(success)
    .catch(fail);
}
export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseDetail,
  like,
  unlike,
  likeListOfUser,
  marketList,
};
