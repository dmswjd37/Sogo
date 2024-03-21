import React from 'react';

const SliderSection = () => {
  return (
    <section className="slider_section">
      <div className="container">
        <div className="detail-box col-md-9 mx-auto px-0">
          <h1>Finding Parking Lots Made Easy</h1>
          <p>
            Necessitatibus non ducimus hic dolor? Maiores itaque vitae sit blanditiis porro, a expedita ex. Totam vel sed obcaecati. Placeat maxime asperiores deleniti tenetur officiis laboriosam laborum a nihil quisquam quis!
          </p>
        </div>
        <div className="find_form_container">
          <form action="#">
            <div className="form-row">
              <div className="col-md-4 px-0">
                <div className="form-group">
                  <label htmlFor="">Select Parking</label>
                  <div className="input-group">
                    <select className="form-control">
                      <option data-display="Highway Park">Highway Park</option>
                      <option value="1">Highway Park</option>
                      <option value="2">Highway Park</option>
                      <option value="3">Highway Park</option>
                    </select>
                  </div>
                </div>
              </div>
              <div className="col-md-4 px-0">
                <div className="form-group">
                  <label htmlFor="">Your Name</label>
                  <div className="input-group">
                    <input type="text" className="form-control" placeholder="John doe" />
                  </div>
                </div>
              </div>
              <div className="col-md-4 px-0">
                <div className="form-group">
                  <label htmlFor="">Your Mobile Number</label>
                  <div className="input-group">
                    <input type="text" className="form-control" placeholder="01 2345678910" />
                  </div>
                </div>
              </div>
            </div>
            <div className="btn-box">
              <button type="submit">
                <span>Search Now</span>
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

export default SliderSection;
