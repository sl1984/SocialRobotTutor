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
          "screen":[],
          "renderView": "",
          "inputFields": [],
          "preTestStartTime": "",
          "preTestAnswerDelay": "",
          "preTestEndTime": "",
          "questions":[
                  {
                      id:1,
                      text: 'A stationary object has no resultant forces acting on it',
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
                      hintone: "According to Newton’s First Law, an object remains in the same state of motion unless a resultant force acts on it.",
                      hinttwo: "If the resultant force acting on a stationary object is zero, then the object will remain stationary.",
                      hintthree: "A resultant force acting on a stationary object causes the object to move"
                    },
                  {
                    id:2,
                    text: "According to Newton's first law, when the net force acting on a moving object is zero, the object will",
                    choices:[
                      {
                        id:'a',
                        text:'accelerate'
                      },
                      {
                        id:'b',
                        text:'decelerate'
                      },
                      {
                        id:'c',
                        text:'move with constant speed'
                      },
                      {
                        id:'d',
                        text:'move with constant velocity'
                      },
                    ],
                    correct: 'd',
                    hintone: "According to Newton's first law, a moving object will accelerate or decelerate when a resultant force acts on it.",
                    hinttwo: "Velocity is determined by both speed and direction",
                    hintthree: ""
                  },
                  {
                    id:3,
                    text: "Which of Newton's Laws explains why satellites need very little fuel to stay in orbit?",
                    choices:[
                      {
                        id:'a',
                        text:"Newton's First Law of Motion"
                      },
                      {
                        id:'b',
                        text:"Newton's Second Law of Motion"
                      },
                      {
                        id:'c',
                        text:"Newton's Third Law of Motion"
                      },
                    ],
                    correct: 'a',
                    hintone: "It is a near vacuum in space so the satellite encounters almost no friction to slow them down.",
                    hinttwo: "Once a satellite is in orbit, the only net force acting on it is the inward force of gravity.",
                    hintthree: ""
                  },
                  {
                    id:4,
                    text: "Calculate the force (N) needed to accelerate an object with a mass of 10 kg by 2 m/s2",
                    choices:[
                      {
                        id:'a',
                        text:"5 N"
                      },
                      {
                        id:'b',
                        text:"20 N"
                      },
                      {
                        id:'c',
                        text:"40 N"
                      },
                      {
                        id:'d',
                        text:"0 N"
                      },
                    ],
                    correct: 'b',
                    hintone: "Do you remember the formula of Newton's Second Law of Motion?",
                    hinttwo: "Force (N)  = Mass (kg) × Acceleration m / s2 ",
                    hintthree: ""
                  },
                  {
                    id:5,
                    text: "A force of 50 N is applied to an object with a mass of 0.5 kg. Calculate the acceleration of the object",
                    choices:[
                      {
                        id:'a',
                        text:'25 m/s2'
                      },
                      {
                        id:'b',
                        text:'250 m/s2'
                      },
                      {
                        id:'c',
                        text:'100 m/s2'
                      },
                      {
                        id:'d',
                        text:'10 m/s2'
                      },
                    ],
                    correct: 'c',
                    hintone: "Force (N)  = Mass (kg) × Acceleration (m / s2) ",
                    hinttwo: "Acceleration (m / s2) =  Force (N) / Mass (kg)",
                    hintthree: ""
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

            // subscribes to DataDelivery event.
            furhat.subscribe('furhatos.app.furgui.DataDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    buttons: data.buttons,
                    inputFields: data.inputFields
                })
            })

            // subscribes to ScreenDelivery event.
            furhat.subscribe('furhatos.app.furgui.ScreenDelivery', function (data) {
                INSTANCE.setState({
                    ...this.state,
                    screen: data.screen,
                })
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('SpeechDone', function () {
                INSTANCE.setState({
                    ...this.state,
                    speaking: false
                })
            })

            // This event contains to data so we defined it inline in the flow
            furhat.subscribe('HelpText', function () {
                INSTANCE.setState({
                    ...this.state,
                    showHelp: true
                })
            })

            // Method that we can access outside of this callback, for sending
            INSTANCE.sendEvent = (data) => {
              furhat.send({
                event_name: data.event_name,
                data: data.data
              })
            }
        })
    }

    clickButton = (button) => {
        console.log("button clicked:" +button)

        this.setState({
            ...this.state,
            speaking: true,
            showHelp: false,
            renderView: button,
            preTestStartTime: currentDate,
            preTestAnswerDelay: currentDate

        })

        if(button == "Quiz"){
            var currentDate = new Date()
            this.restartQuiz()
        }

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

    restartQuiz = () => {
        console.log('Restarting Quiz')
        this.setCurrent(1)
        this.setScore(0)
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
            <Row className={"show-grid"} height="40">
                <Col sm={12}>
                    <Navbar bg="light">
                      <Navbar.Header>
                        <Navbar.Brand>
                          <img alt="" src="assets/img/robot.webp" width="20" height="20" className="d-inline-block align-top"/>
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
