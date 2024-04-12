import React, { useEffect } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import HeaderContainer from './components/HeaderContainer';
import BackgroundImage from './components/BackgroundImage';
import Footer from './components/Footer';
import LoginPage from './Member/Login';
import SignupPage from './Member/Signup';

function App() {
    const location = useLocation();
    const isLogIn = localStorage.getItem('access');
    
    useEffect(() => {
        // 컴포넌트가 렌더링될 때마다 실행되는 부분
        if (location.pathname === '/') {
            document.body.classList.remove('sub_page');
        } else {
            document.body.classList.add('sub_page');
        }
    }, [location.pathname]);

    return (
        <div>
            <div className="hero_area">
                <BackgroundImage />
                <HeaderContainer isLogIn={isLogIn} />
            </div>
            <section class="about_section layout_padding">
                <Routes>
                    {/* LoginPage에 setIsLogIn 함수 전달 */}
                    <Route path="/Login" element={<LoginPage />} />
                    <Route path="/signup" element={<SignupPage />} />
                </Routes>
            </section>
            <Footer />
        </div>
    );
}

export default App;
