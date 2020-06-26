import React, {Component} from 'react';
import { Grid, Row, Col, Nav, NavItem} from 'react-bootstrap'


class Lecture extends Component{
  constructor(props) {
          super(props)
          this.state = {
            "speaking": false,
            "eventKey": 1
          }
  }

  handleSelect(eventKey) {
      this.setState({eventKey});
      console.log(`selected ${eventKey}`);
  }

  render(){

    if (this.state.eventKey == 2){
        var content = "In an inertial frame of reference, the vector sum of the forces F on an object is equal to the mass m of that object multiplied by the acceleration a of the object: F = ma. (It is assumed here that the mass m is constant)"
    } else {
        var content = "In an inertial frame of reference, an object either remains at rest or continues to move at a constant velocity, unless acted upon by a force"
    }

    return(

            <Grid>
              <Row>
                <Col sm={7}><h4><b>Newton's Laws of Motion</b></h4></Col>
                <Col sm={1}><img alt="Isaac Newton" width="100" height="100" className="align-right" src="assets/img/newton.webp"/>
                </Col>
              </Row>
              <Row>
                <Col sm={8}>
                <Nav bsStyle="tabs"  activeKey="{eventKey}" onSelect={k => this.handleSelect(k)}>
                    <NavItem eventKey="1">
                      First Law
                    </NavItem>
                    <NavItem eventKey="2">
                      Second Law
                    </NavItem>
                </Nav>
                </Col>
              </Row>
              <Row>
                <Col sm={8}>
                <br/>
                </Col>
              </Row>
              <Row>
                <Col sm={8}>
                {content}
                </Col>
              </Row>
            </Grid>



      )
  }
}

export default Lecture
