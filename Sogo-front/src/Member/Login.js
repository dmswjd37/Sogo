import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [activeTab, setActiveTab] = useState('customer'); // 초기값을 '고객 로그인'으로 설정

  const handleTabChange = (tab) => {
    setActiveTab(tab);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    fetch("http://localhost:8089/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        password: password
      }),
      credentials: 'include'
    })
    .then((response) => {
      if (response.status === 200) {
        const accessToken = response.headers.get('access');
        localStorage.setItem("access", accessToken);
        navigate('/'); 
      }
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  };
  
  return (
    <div className="contents">
      <div className="container">
        <div className="row align-items-center justify-content-center bg-white">
          <div className="col-md-12">
            <div className="form-block mx-auto">
              <div className="text-center mb-5">
                <h3 className="text-uppercase">Login to <strong>SOGO</strong></h3>
              </div>
              <div className="tab-menu">
                <ul className="nav nav-tabs nav-justified">
                  <li className="nav-item">
                    <button className={`nav-link w-100 ${activeTab === 'customer' ? 'active text-warning' : ''}`} onClick={() => handleTabChange('customer')}>
                      고객 로그인
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className={`nav-link w-100 ${activeTab === 'business' ? 'active text-warning' : ''}`} onClick={() => handleTabChange('business')}>
                      사업자 로그인
                    </button>
                  </li>
                </ul>
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
                <button type="submit" className="btn btn-block py-2 btn-primary">{activeTab === 'customer' ? '로그인' : '사업자 로그인'}</button>
                {activeTab === 'customer' && (
                  <div>
                    <span className="text-center my-3 d-block">or</span>
                    <div>
                      <a href="/" className="btn btn-block py-2 btn-google"><span className="icon-google mr-3"></span> Login with Google</a>
                    </div>
                  </div>
                )}
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
