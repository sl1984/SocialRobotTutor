import React, {Component} from 'react'
import Furhat from 'furhat-gui'
import { Container, Grid, Row, Col, ButtonGroup, Navbar} from 'react-bootstrap'
import Button from './Button'
import Input from './Input'
import QuestionList from './QuestionList'
import ProgressBox from './ProgressBox'
import Results from './Results'
import Lecture from './Lecture'
import HelpBox from './HelpBox'

class App extends Component {

    constructor(props) {
        super(props)
        this.state = {
          "speaking": false,
          "showHelp": false,
          "buttons": [],
          "renderView": "",
          "inputFields": [],
          "preTestStartTime": "",
          "preTestAnswerDelay": "",
          "preTestEndTime": "",
          "questions":[
                  {
                    id:1,
                    text: 'Which of the following statements is false?',
                    choices:[
                      {
                        id:'a',
                        text:'If the net force on a body is zero, then its velocity will not change'
                      },
                      {
                        id:'b',
                        text:'An unbalanced force on a body will always impact the object speed'
                      },
                      {
                        id:'c',
                        text:'The reason why initially moving objects tend to come to rest in our everyday life is because they are being acted upon by an unbalanced force'
                      },
                    ],
                    correct: 'b',
                    hintone: 'Hint 1',
                    hinttwo: 'Hint 2',
                    hintthree: 'Hint 3'
                  },
                  {
                    id:2,
                    text: 'Which of the following statements most correctly describes Newton’s first law of Motion?',
                    choices:[
                      {
                        id:'a',
                        text:'An object will move until a net force acts on the object.'
                      },
                      {
                        id:'b',
                        text:'An object will not move unless a net force acts on the object.'
                      },
                      {
                        id:'c',
                        text:'An object will change direction if a net force acts on the object.'
                      },
                      {
                        id:'d',
                        text:'An object will not change its velocity unless a net force acts on the object.'
                      },
                    ],
                    correct: 'd',
                    hintone: 'Hint 1',
                    hinttwo: 'Hint 2',
                    hintthree: 'Hint 3'
                  },
                  {
                    id:3,
                    text: 'A car speeds up. Which claim about the forces acting on on the car must be true?',
                    choices:[
                      {
                        id:'a',
                        text:'there is not enough information'
                      },
                      {
                        id:'b',
                        text:'there is no net force on the car'
                      },
                      {
                        id:'c',
                        text:'there is a net force on the car'
                      },
                      {
                         id:'d',
                         text:'there are no forces on the car'
                      },
                    ],
                    correct: 'c',
                    hintone: 'Hint 1',
                    hinttwo: 'Hint 2',
                    hintthree: 'Hint 3'
                  },
                  {
                    id:4,
                    text: 'An aeroplane flies with constant velocity, which claim about the forces acting on the aeroplane must be true?',
                    choices:[
                      {
                        id:'a',
                        text:'there are no forces on the aeroplane'
                      },
                      {
                        id:'b',
                        text:'there is no net force on the aeroplane '
                      },
                      {
                        id:'c',
                        text:'there is not enough information'
                      },
                      {
                        id:'d',
                        text:'there is a net force on the aeroplane'
                      },
                    ],
                    correct: 'b',
                    hintone: 'Hint 1',
                    hinttwo: 'Hint 2',
                    hintthree: 'Hint 3'
                  },
                  {
                    id:5,
                    text: 'Objects in orbit around the Earth (like a satellite) must have a net force acting on them.',
                    choices:[
                      {
                        id:'a',
                        text:'True'
                      },
                      {
                        id:'b',
                        text:'False'
                      },
                    ],
                    correct: 'a',
                    hintone: 'Hint 1',
                    hinttwo: 'Hint 2',
                    hintthree: 'Hint 3'
                  }
          ],
          "score":0,
          "current":1,
          "hintCounter":0
        }
    }

    setCurrent(current){
        this.setState({current});
    }

    setScore(score){
        this.setState({score});
    }

    setHintCounter(hintCounter){
        this.setState({hintCounter});
    }

    setShowHelp(showHelp){
        this.setState({showHelp});
    }

    setView(renderView){
        this.setState({renderView});
    }

    setPreTestStartTime(preTestStartTime){
        this.setState({preTestStartTime});
    }

    setPreTestAnswerDelay(preTestAnswerDelay){
        this.setState({preTestAnswerDelay});
    }

    setPreTestEndTime(preTestEndTime){
        this.setState({preTestEndTime});
    }

    componentDidMount() {

        // Needed to access "this" inside our callback
        const INSTANCE = this;

        // Connecting to our skill and subscribing to events
        Furhat(function (furhat) {

            // Our DataDelivery event is getting no custom name and hence gets it's full class name as event name.
            furhat.subscribe('furhatos.app.furgui.DataDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    buttons: data.buttons,
                    inputFields: data.inputFields
                })
                console.log('recieved event: ', data)
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('SpeechDone', function () {
                INSTANCE.setState({
                    ...this.state,
                    speaking: false
                })
                console.log('recieved event: Speech Done')
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('HelpText', function () {
                INSTANCE.setState({
                    ...this.state,
                    showHelp: true
                })
                console.log('recieved event: HelpText')
            })

            // Method that we can access outside of this callback, for sending
            INSTANCE.sendEvent = (data) => {
              furhat.send({
                event_name: data.event_name,
                data: data.data
              })
              console.log('send event:', data)
            }
        })
    }

    clickButton = (button) => {
        console.log('button clicked:')
        if(button == "Quiz"){
            var currentDate = new Date()
        }
        this.setState({
            ...this.state,
            speaking: true,
            showHelp: false,
            renderView: button,
            preTestStartTime: currentDate,
            preTestAnswerDelay: currentDate

        })
        this.sendEvent({
          event_name: "ClickButton",
          data: button
        })
    }

    variableSet = (variable, value) => {
        this.setState({
            ...this.state,
            speaking: true
        })
        this.sendEvent({
          event_name: "VariableSet",
          data: {
            variable,
            value
          }
        })
    }

    sendMessage = (message) => {
        console.log('Sending message to Tutor')
        this.setState({
            ...this.state,
            speaking: true

        })
        this.sendEvent({
          event_name: "Message",
          data: message
        })
    }

    sendNotification = (event_type, message) => {
        console.log('Sending message to Tutor')
        this.setState({
            ...this.state,
            speaking: true

        })
        this.sendEvent({
          event_name: "Notification",
          data: {
              event_type,
              message
          }
        })
    }

    render() {
      if(this.state.renderView == "Quiz"){
          console.log("RenderView: Quiz")

          if(this.state.current > this.state.questions.length){
                console.log("RenderView: Quiz result view")
                var results = <Results {...this.state} />;
                var progressbox='';
                var message = `You got, ${this.state.score}, correct answers out of, ${this.state.questions.length}, questions`;
                var scorebutton = <div><Button key="Check Score" label="Check Score" onClick={() => this.sendMessage(message)} speaking={this.state.speaking} style="info"/></div>;
          }
          else{
                var progressbox = <ProgressBox {...this.state} />
                var results='';

                const currentQuestion = this.state.questions.filter(question => question.id === this.state.current)

                if (this.state.showHelp){
                    if (this.state.hintCounter == 0){
                        var help = "So here is the hint: "+ currentQuestion[0].hintone
                    } else if (this.state.hintCounter == 1) {
                        var help = "So here is the hint: "+ currentQuestion[0].hinttwo
                    } else {
                        var help = "So here is the hint: "+ currentQuestion[0].hintthree
                    }
                    console.log(help)
                    this.state.hintCounter = this.state.hintCounter+1
                    var helpBox = <HelpBox {...this.state} help={help}/>
                }
                var question = <QuestionList {...this.state} setCurrent={this.setCurrent.bind(this)} setScore={this.setScore.bind(this)} setShowHelp={this.setShowHelp.bind(this)} setHintCounter={this.setHintCounter.bind(this)} />;

          }

      } else if(this.state.renderView == "Lecture") {
                  var content = <Lecture {...this.state} />;
      } else if(this.state.renderView == "Pre-Test") {
            console.log("RenderView: Pre-Test")

              if(this.state.current > this.state.questions.length){
                    console.log("RenderView: Pre-Test result view")
                    var results = <Results {...this.state} />;
                    var progressbox='';
                    var message = `You got, ${this.state.score}, correct answers out of, ${this.state.questions.length}, questions`;
                    var scorebutton = <div><Button key="Check Score" label="Check Score" onClick={() => this.sendMessage(message)} speaking={this.state.speaking} style="info"/></div>;
              }
              else{
                    var progressbox = <ProgressBox {...this.state} />
                    var results='';

                    const currentQuestion = this.state.questions.filter(question => question.id === this.state.current)
                    var question = <QuestionList {...this.state} setCurrent={this.setCurrent.bind(this)} setScore={this.setScore.bind(this)} setShowHelp={this.setShowHelp.bind(this)} setHintCounter={this.setHintCounter.bind(this)}/>;

              }
      } else if(this.state.renderView == "Register") {
            var content = this.state.inputFields.map((label) =>
                <Input key={label} label={label} onSave={this.variableSet} speaking={this.state.speaking}/>)

      } else {
            var welcomeText = "Welcome to the Physics online tutorial"
      }


      return (
          <Grid>
            <Row className={"show-grid"} height="60">
                <Col sm={12}>
                    <Navbar bg="light">
                      <Navbar.Header>
                        <Navbar.Brand>
                          <img alt="" src="assets/img/robot.webp" width="40" height="40" className="d-inline-block align-top"/>
                          {' '}
                            Social Robot Tutor
                        </Navbar.Brand>
                      </Navbar.Header>
                    </Navbar>
                </Col>
            </Row>
            <Row className={"show-grid"}>
                <Col sm={3}>
                    <ButtonGroup vertical>
                    <div className="well">
                    { this.state.buttons.map((label) =>
                        <Button key={label} label={label} onClick={this.clickButton} speaking={this.state.speaking} style="primary"/>
                    )}
                    </div>
                    </ButtonGroup>
                </Col>
                <Col sm={9}>
                    <div className="well">
                        {welcomeText}
                        {progressbox}
                        {question}
                        {results}
                        {content}
                        {scorebutton}

                    </div>
                    {helpBox}
                </Col>
             </Row>
             <Row className={"show-grid"}>
                <Col sm={12}>
                    <div className="well">
                    <h6>"Copyright here"</h6>
                    </div>
                </Col>
            </Row>
          </Grid>
        )
    }

}

export default App;
