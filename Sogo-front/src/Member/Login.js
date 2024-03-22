import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function LoginPage() {
  // 로그인 페이지로 이동될 때 sub_page 클래스 추가
  document.body.classList.add('sub_page');

  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 로그인 요청 보내기
    try {
      const response = await fetch('http://localhost:8089/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          email: email,
          password: password
        })
      });

      if (response.ok) {
        // 로그인 성공 시 처리
        // 예를 들어 로그인 상태를 저장하고 다음 페이지로 이동하는 등의 작업 수행
        console.log('로그인 성공');
        document.body.classList.remove('sub_page');
        navigate('/');
      } else {
        // 로그인 실패 시 처리
        console.error('로그인 실패');
      }
    } catch (error) {
      console.error('로그인 요청 중 오류 발생:', error);
    }
  }
  
  return (
    <div className="contents">
      <div className="container">
        <div className="row align-items-center justify-content-center bg-white">
          <div className="col-md-12">
            <div className="form-block mx-auto">
              <div className="text-center mb-5">
                <h3 className="text-uppercase">Login to <strong>SOGO</strong></h3>
              </div>
              <form onSubmit={handleSubmit}>
                <div className="form-group first">
                  <label htmlFor="username">Useremail</label>
                  <input type="text" className="form-control" placeholder="your-email@gmail.com" id="username"
                    value={email} onChange={(e) => setEmail(e.target.value)} 
                  />
                </div>
                <div className="form-group last mb-3">
                  <label htmlFor="password">Password</label>
                  <input type="password" className="form-control" placeholder="Your Password" id="password"
                    value={password} onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className="d-sm-flex mb-5 align-items-center">
                  <span className="ml-auto">
                    <a href="/" className="forgot-pass">Forgot Password</a>&nbsp;|&nbsp;
                    <Link to="/Signup" className="forgot-pass">Create account</Link>
                  </span> 
                </div>
                <input type="submit" value="Log In" class="btn btn-block py-2 btn-primary" />
                <span class="text-center my-3 d-block">or</span>
                <div>
                  <a href="/" className="btn btn-block py-2 btn-google"><span className="icon-google mr-3"></span> Login with Google</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
