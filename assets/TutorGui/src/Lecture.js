import React, {Component} from 'react';
import { Grid, Row, Col, Nav, NavItem} from 'react-bootstrap'

class Lecture extends Component{
  constructor(props) {
          super(props)
          this.state = {
            "speaking": false,
          }
  }

  render(){

    var src = `assets/img/${this.props.screen}`
    var screen = <img alt="Isaac Newton" width="600" height="450" className="align-right" src={src}/>

    return(

            <Grid>
              <Row>
                <Col sm={8}>
                    {screen}
                </Col>
              </Row>
            </Grid>



      )
  }
}

export default Lecture
