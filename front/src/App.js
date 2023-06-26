import React from "react";
import axios from "axios";

export default function App() {
  let BaseCss = () => {

  }

  const BASE_URL = "http://localhost:8080"

  const data = {
    name: "짭종",
    email: "2721123210@naver.com",
    password: "4721",
    phone: "01098745612",
  }

  axios.get(BASE_URL + "/members?page=1&size=10").then(function (response) {
    console.log(response.data)
  })

  axios.get(BASE_URL + "/find/1").then(function (response) {
    console.log(response.data)
  })

  axios.post(BASE_URL, data).then(function (response) {
    console.log(response);
  })

  axios.delete(BASE_URL + "/delete/2").then(function (response) {
    console.log(response);
  })

  axios.patch(BASE_URL + "/update/10", data).then(function (response) {
    console.log(response)
  })

  return (
      <div className="container">
        <div className="input">
          <input type="text" placeholder="아이디를 입력하세요."/>
          <input type="password" placeholder="비밀번호를 입력하세요."/>
        </div>
        <form>
          <div className="memberCreate">
            <button>회원가입</button>
          </div>
          <div className="findMember">
            <button>아이디/비밀번호 찾기</button>
          </div>
          <div className="login">
            <button>로그인</button>
          </div>
        </form>
      </div>
  );
};
