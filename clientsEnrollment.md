# Clients Enrollment IDD

## Overview
This interface is used to check, generate and sign enrollment/denial documents.

## Concept of Execution

@startuml

title "Enrollment System Diagram"

actor User as USER
boundary "Web GUI" as GUI
entity "Enrollment system" as ES
control "Check Reputation Service" as RS
database Clients

USER -> GUI : perform client check
  activate GUI
  GUI -> ES : POST **/check**
  activate ES
    ES -> RS : Get client reputation
    activate RS
    RS -> ES : risk info
    deactivate RS
  ES -> GUI: risk info
deactivate ES
GUI -> USER : **Risk info**
deactivate GUI


alt Risk info <= 99
USER -> GUI : generate enrollment document
  activate GUI
  GUI -> ES : POST **/generate**
  activate ES
  ES -> GUI: enrollment document
deactivate ES
GUI -> USER : **Enrollment Document**
deactivate GUI
else Risk info > 99
USER -> GUI : generate denial document
  activate GUI
  GUI -> ES : POST **/generate**
  activate ES
  ES -> GUI: denial document
deactivate ES
GUI -> USER : **Denial Document**
deactivate GUI
end

USER -> GUI : sign document
  activate GUI
  GUI -> ES : POST **/sign**
  activate ES
    ES -> Clients: save client
    activate Clients
    Clients -> ES : 201 created
    deactivate Clients
    ES -> GUI: document signed
deactivate ES
GUI -> USER : **Document Signed**
deactivate GUI


@enduml


## Application-Level Messages

### `POST /playground/enrollment/clients/check`

#### Request

* Description: 

* Body:
~~~
{
    "name" : "OS",
    "income" : 100.2
}
~~~

* Example
~~~
http://localhost:8801/playground/enrollment/clients/check
~~~

#### Responses

##### HTTP status code 200

* Description: full Client check completed successfully
* Content-type: application/json
* Format: text
* Example: "HIGH_RISK", "MEDIUM_RISK", "NO_RISK"


##### HTTP status code 400

* Description: Bad request is returned when payload is missing or incomplete
* Content-type: application/json
* Format: text


##### HTTP status code 500

* Description: Internal server error is returned when error occurs while client check was performed. 
* Content-type: application/json
* Format: text


### `POST /playground/enrollment/clients/generate`

#### Request

* Description: 

* Body:

~~~
{
    "name" : "OS",
    "income" : 100.2
}
~~~

* Example

~~~
http://localhost:8801/playground/enrollment/clients/generate
~~~


#### Responses

##### HTTP status code 201

* Description: Denial document was successfully created 
* Content-type: application/json
* Format: application/json
* Example: 

~~~
{
    "type": "denial",
    "message": "Not allowed to register client OS",
    "client": {
        "name": "OS",
        "id": "c8e70043-03e7-417c-8f85-1f0acf72306d"
    },
    "uuid": "9526731a-aab3-4103-a76c-cc303e3776fc"
}
~~~

##### HTTP status code 201

* Description: Enrollment document was successfully created 
* Content-type: application/json
* Format: application/json
* Example: 

~~~
{
    "type": "enrollment",
    "message": "Welcome, OS",
    "client": {
        "name": "OS",
        "id": "34e675df-b83e-4b6c-96c7-30766c1e6109"
    },
    "uuid": "8a543f99-125e-44e3-97cf-46fa4c007b2a"
}
~~~


##### HTTP status code 400

* Description: Bad request is returned when payload is missing or incomplete
* Content-type: application/json
* Format: text


##### HTTP status code 500

* Description: Internal server error is returned when error occurs while generating documentation. 
* Content-type: application/json
* Format: text


### `POST /playground/enrollment/clients/sign`

#### Request

* Description: Sign enrollment/denial documents.

* Body:
~~~
{
    "type": "enrollment",
    "message": "Welcome, OS",
    "client": {
        "name": "OS",
        "id": "34e675df-b83e-4b6c-96c7-30766c1e6109"
    },
    "uuid": "8a543f99-125e-44e3-97cf-46fa4c007b2a"
}
~~~

* Example
~~~
http://localhost:8801/playground/enrollment/clients/sign
~~~


#### Responses

##### HTTP status code 200

* Description: 
* Content-type: application/json
* Format: text
* Example: "Document signed."
