import React, { useState } from 'react';
import { Link } from "react-router-dom";

function Header() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // 로그인 상태에 따라 로그인/로그아웃 버튼 텍스트 변경

    const handleLogout = () => {
        setIsLoggedIn(false);
    };

    const handleLogin = () => {
        // 로그인 성공 시에 호출되는 함수
        setIsLoggedIn(true);
    };

    return (
        <header className="header_section">
            <div className="container">
                <nav className="navbar navbar-expand-lg custom_nav-container">
                    <a className="navbar-brand" href="index.html">
                        <span>
                            Paspark
                        </span>
                    </a>

                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className=""></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav">
                            <li className="nav-item active">
                                <a className="nav-link" href="index.html">Home <span className="sr-only">(current)</span></a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="about.html"> About</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="pricing.html">Pricing</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="why.html">Why Us</a>
                            </li>
                            {isLoggedIn ? (
                                <li className="nav-item">
                                    <button onClick={handleLogout} className="nav-link">Logout</button>
                                </li>
                            ) : (
                                <li className="nav-item">
                                    <Link className="nav-link" to="/Login" onClick={handleLogin}>Login</Link>
                                </li>
                            )}
                        </ul>
                        <form className="form-inline">
                            <button className="btn my-2 my-sm-0 nav_search-btn" type="submit">
                                <i className="fa fa-search" aria-hidden="true"></i>
                            </button>
                        </form>
                    </div>
                </nav>
            </div>
        </header>
    );
}

export default Header;
