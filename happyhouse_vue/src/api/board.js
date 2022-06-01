import { apiInstance } from "./index.js";

const api = apiInstance();

function boardList(navigationParam, success, fail) {
  console.log(navigationParam);

  let queryString = `curPageNum=${navigationParam.curPageNum}&itemPerPage=${navigationParam.itemPerPage}&keyWord=${navigationParam.keyWord}&searchWord=${navigationParam.searchWord}`;
  api.get(`/board?${queryString}`).then(success).catch(fail);
}
function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function detailBoard(boardno, success, fail) {
  return api.get(`/board/detail/${boardno}`).then(success).catch(fail);
}
function getComment(boardno, success, fail) {
  api.get(`/board/comment/${boardno}`).then(success).catch(fail);
}

function registBoard(formData, token, success, fail) {
  api
    .post("/board", formData, {
      headers: {
        "JWT-AUTHENTICATION": token,
        "Content-Type": "multipart/form-data",
      },
    })
    .then(success)
    .catch(fail);
}
function registComment(comment, token, success, fail) {
  api
    .post(`/board/comment/${comment.boardno}`, JSON.stringify(comment), {
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
function removeComment(no, token, success, fail) {
  api
    .delete(`/board/comment/${no}`, {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
function deleteBoard(no, token, success, fail) {
  api
    .delete(`/board/${no}`, {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
function editBoard(board, token, success, fail) {
  api
    .put(`/board`, board, {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
export {
  boardList,
  writeArticle,
  detailBoard,
  getComment,
  registBoard,
  registComment,
  fileDownload,
  removeComment,
  deleteBoard,
  editBoard,
};
