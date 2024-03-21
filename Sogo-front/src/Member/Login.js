import React from 'react';
import { Link } from 'react-router-dom';

function LoginPage() {
  // 로그인 페이지로 이동될 때 sub_page 클래스 추가
  document.body.classList.add('sub_page');
  
  return (
    <div className="contents">
      <div className="container">
        <div className="row align-items-center justify-content-center bg-white">
          <div className="col-md-12">
            <div className="form-block mx-auto">
              <div className="text-center mb-5">
                <h3 className="text-uppercase">Login to <strong>SOGO</strong></h3>
              </div>
              <form action="#" method="post">
                <div className="form-group first">
                  <label htmlFor="username">Username</label>
                  <input type="text" className="form-control" placeholder="your-email@gmail.com" id="username" />
                </div>
                <div className="form-group last mb-3">
                  <label htmlFor="password">Password</label>
                  <input type="password" className="form-control" placeholder="Your Password" id="password" />
                </div>
                <div className="d-sm-flex mb-5 align-items-center">
                  <span className="ml-auto">
                    <a href="/" className="forgot-pass">Forgot Password</a>&nbsp;|
                    <Link to={`/`} className="forgot-pass">Create account</Link>
                  </span> 
                </div>
                <input type="submit" value="Log In" className="btn btn-block py-2 btn-primary" />
                <span className="text-center my-3 d-block">or</span>
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
