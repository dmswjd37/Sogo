import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Header = ({isLogIn}) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        fetch('http://localhost:8089/logout', {
            method: 'POST',
            credentials: 'include',
        })
        .then(response => {
            if (response.ok) {
                localStorage.clear();
                navigate('/');
            } else {
                throw new Error('Logout failed');
            }
        })
        .catch(error => {
            console.error('Logout error:', error);
        });
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
                            <li className="nav-item">
                                {isLogIn ? 
                                    <Link className="nav-link" onClick={handleLogout}>Logout</Link> :
                                    <Link className="nav-link" to="/Login">Login</Link>
                                }
                            </li>
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
