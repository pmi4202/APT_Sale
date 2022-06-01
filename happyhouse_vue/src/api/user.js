import { apiInstance } from "@/api/index.js";

const http = apiInstance();

// 로그인 정보를 서버에 보낸다.
//유저 정보객체,성공 시 호출 델리게이트 , 실패시 호출 델리게이트
async function login(user, success, fail) {
  //자체는 비동기 함수인데 , 요청오기까지 기다리는 함수
  await http.post("/login", JSON.stringify(user)).then(success).catch(fail);
}

//사용자의 정보를 가져오기 위해 인증을 위한 토큰과 유저 아이디를 보낸다.
async function findById(userid, success, fail) {
  http.defaults.headers["JWT-AUTHENTICATION"] =
    sessionStorage.getItem("access-token");
  await http.get(`/user/${userid}`).then(success).catch(fail);
}
function deleteUser(token, success, fail) {
  http
    .delete("/user", {
      headers: {
        "JWT-AUTHENTICATION": token,
      },
    })
    .then(success)
    .catch(fail);
}
function registUser(user, success, fail) {
  http.post("/user", JSON.stringify(user)).then(success).catch(fail);
}

function update(user, success, fail) {
  http.put("/user", JSON.stringify(user)).then(success).catch(fail);
}
export { login, findById, registUser, update, deleteUser };
