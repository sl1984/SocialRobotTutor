import React, {Component} from 'react';
import { Nav, NavItem } from 'react-bootstrap'


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
        var content = "Second Law of Motion"
    } else if (this.state.eventKey == 3){
        var content = "Third Law of Motion"
    } else {
        var content = "First Law of Motion"
    }

    return(
        <div>
        <div>
        Newtons Laws of Motion <br/>
        <Nav bsStyle="pills" activeKey="{eventKey}" onSelect={k => this.handleSelect(k)}>
            <NavItem eventKey="1">
              First Law
            </NavItem>
            <NavItem eventKey="2">
              Second Law
            </NavItem>
            <NavItem eventKey="3">
              Thrird Law
            </NavItem>
        </Nav>
        </div>
        <div className="well">
            {content}
        </div>
        </div>

      )
  }
}

export default Lecture
