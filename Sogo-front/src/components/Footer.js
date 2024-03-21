import React from 'react';

const Footer = () => {
  return (
    <section className="info_section">
      <div className="container">
        <div className="info_top">
          <div className="row">
            <div className="col-md-6 col-lg-3 info_col">
              <div className="info_form">
                <h4>Stay Connected</h4>
                <form action="">
                  <input type="text" placeholder="Enter Your Email" />
                  <button type="submit">Subscribe</button>
                </form>
                <div className="social_box">
                  <a href="#"><i className="fa fa-facebook" aria-hidden="true"></i></a>
                  <a href="#"><i className="fa fa-twitter" aria-hidden="true"></i></a>
                  <a href="#"><i className="fa fa-linkedin" aria-hidden="true"></i></a>
                  <a href="#"><i className="fa fa-instagram" aria-hidden="true"></i></a>
                </div>
              </div>
            </div>
            <div className="col-md-6 col-lg-3 info_col">
              <div className="info_detail">
                <h4>About Us</h4>
                <p>Necessitatibus, culpa, totam quod neque cum officiis odio, excepturi magnam incidunt voluptates sed voluptate id expedita sint! Cum veritatis iusto molestiae reiciendis deserunt vel odio incidunt, tenetur itaque. Ullam, iste!</p>
              </div>
            </div>
            <div className="col-md-6 col-lg-3 info_col">
              <div className="info_detail">
                <h4>Online Booking</h4>
                <p>Accusantium quis architecto, necessitatibus libero nemo facere perferendis obcaecati pariatur magni quod praesentium provident nisi impedit nobis omnis. Assumenda vero impedit dolorum perspiciatis, ipsa quasi corrupti numquam.</p>
              </div>
            </div>
            <div className="col-md-6 col-lg-3 info_col">
              <h4>Contact us</h4>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit</p>
              <div className="contact_nav">
                <a href="#"><i className="fa fa-map-marker" aria-hidden="true"></i><span>Location</span></a>
                <a href="#"><i className="fa fa-phone" aria-hidden="true"></i><span>Call : +01 123455678990</span></a>
                <a href="#"><i className="fa fa-envelope" aria-hidden="true"></i><span>Email : demo@gmail.com</span></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Footer;
