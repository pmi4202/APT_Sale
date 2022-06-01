import { apiInstance } from "./index.js";

const api = apiInstance();

function noticeList(navigationParam, success, fail) {
  console.log(navigationParam);

  let queryString = `curPageNum=${navigationParam.curPageNum}&itemPerPage=${navigationParam.itemPerPage}&keyWord=${navigationParam.keyWord}&searchWord=${navigationParam.searchWord}`;
  api.get(`/notice?${queryString}`).then(success).catch(fail);
}
function detailNotice(noticeno, success, fail) {
  console.log("확인:", noticeno);
  return api.get(`/notice/${noticeno}`).then(success).catch(fail);
}
function registNotice(notice, token, success, fail) {
  api
    .post(`/notice`, JSON.stringify(notice), {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
function fileDownload(file, success, fail) {
  let queryString = `originalFileName=${file.originalFileName}&saveFileName=${file.saveFileName}&saveFolder=${file.saveFolder}`;
  const link = document.createElement("a");
  link.href = api.getUri() + `/board/file?${queryString}`;
  console.log(link.href);
  link.click();
  link.remove();
  let k = 0;
  if (k == 1) {
    console.log(success, fail);
  }
}
function deleteNotice(no, token, success, fail) {
  api
    .delete(`/notice/${no}`, {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
function editNotice(notice, token, success, fail) {
  api
    .put(`/notice`, notice, {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}

export {
  noticeList,
  registNotice,
  detailNotice,
  fileDownload,
  deleteNotice,
  editNotice,
};
