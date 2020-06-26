import React, {Component} from "react"
import {
  Col,
  Button as BootstrapButton,
} from 'react-bootstrap';

class Button extends Component {
  constructor(props){
      super(props)
  }

  render() {
    let {label, speaking, onClick, style, bsSize} = this.props

    return (
        <BootstrapButton
            bsStyle={style}
            bsSize={bsSize}
            key={label}
            className={`button`}
            onClick={() => { onClick(label) }}
            disabled={ speaking }
        >
          { label }
        </BootstrapButton>
    )
  }
}

export default Button
