import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignupPage() {
  // 페이지로 이동될 때 sub_page 클래스 추가
  document.body.classList.add('sub_page');

  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // JSON 형식으로 데이터 생성
    const userData = {
      email: email,
      name: name,
      password: password
    };

    try {
      // 회원가입 요청 보내기
      const response = await fetch('http://localhost:8089/join', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
      });

      if (response.ok) {
        // 회원가입 성공 시 처리
        console.log('회원가입 성공');
        navigate('/login'); // 로그인 페이지로 이동
      } else {
        // 회원가입 실패 시 처리
        console.error('회원가입 실패');
      }
    } catch (error) {
      console.error('회원가입 요청 중 오류 발생:', error);
    }
  }
  
  return (
    <div className="contents">
      <div className="container">
        <div className="row align-items-center justify-content-center bg-white">
          <div className="col-md-12">
            <div className="form-block mx-auto">
              <div className="text-center mb-5">
                <h3 className="text-uppercase">Sign up <strong>SOGO</strong></h3>
              </div>
              <form onSubmit={handleSubmit}>
                <div className="form-group first">
                  <label htmlFor="email">Email</label>
                  <input type="email" className="form-control" placeholder="your-email@gmail.com" id="email"
                    value={email} onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="name">Name</label>
                  <input type="text" className="form-control" placeholder="Your Name" id="name"
                    value={name} onChange={(e) => setName(e.target.value)}
                  />
                </div>
                <div className="form-group last mb-3">
                  <label htmlFor="password">Password</label>
                  <input type="password" className="form-control" placeholder="Your Password" id="password"
                    value={password} onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="confirmPassword">Confirm Password</label>
                  <input type="password" className="form-control" placeholder="Confirm Password" id="confirmPassword"
                    value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)}
                  />
                </div>
                <div className="d-sm-flex mb-5 align-items-center">
                  <span className="ml-auto">
                  </span> 
                </div>
                <input type="submit" value="Sign Up" class="btn btn-block py-2 btn-primary" />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignupPage;
