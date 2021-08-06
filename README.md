# chickchickrun21
ChickChickRun Assignment

## Table of Contents

        Table of Contents
        Preamble
            Product Owner
            Rockstars
        Project Deadline
        Project Specification
        Considerations
        Useful Links
            For more information visit our other sections
        Risk Register
        Tenants of Design
        Social Contract

## Preamble

This is the online repository for the ChickChickRun assignment. 

## Summary 

The customer would like an on-boarding system for their employees. ChickChickRun is an engineering
and tech company. Their most popular product is a browser. The system must take details of the
employee skills to map them to the training requirements. For example, if the new staff member has
not completed Agile training they must do so as it is mandatory. The system should be clean and
simple. The system needs to take into account the usual security requirements. It must be possible to
upload files or images which may be images of certificates for courses completed. Jpgs are fine. The
administrator should be able to access detailed information and edit as appropriate. Once the client
enters details it should not be able to be changed by the client

Our product will be delivered using an Agile methodology that embraces the DevOps culture. Please note that our culture embraces change and these documents are treated as living, breathing artefacts that will be continuously updated.

### Product Owner
Ruth G. Lennon

### Rockstars
        Akhilesh Sasimohan
        Eyituoyo George Ikomi
        Jomy Jose
        Juarez Alvares Barbosa Junior
        Maria Pavlova
        Jorge Racionero
        Miguel Pinar 

## Project Deadline

Refer to BB for deadlines
  
## Project Specification  
    Clean and simple design using draw.io (https://app.diagrams.net/)
    User access levels (client, administrator)
    Includes at least one self developed api and one webservice
    To be run over Azure
    Frameworks : Terraform and Java 
    Database : mySQL
    Database persistence technology : Azure Backup
    Define the business Requirements : 
        - The system must take details of the employee skills to 
        - Map the skills to the training requirements
        - The system should be clean and simple
        - The system needs to take into account the usual security requirements
        - The system should be able to store uploaded files and images
        - The administrator should be able to access detailed information and edit as appropriate
        - Once the client enters details it should not be able to be changed by the client
    Named queries and database triggers for security
        - This point requires further research. 
        https://docs.microsoft.com/en-us/sql/relational-databases/triggers/manage-trigger-security?view=sql-server-ver15

    Regex for cleansing and validation of data before sending to the database.

## Useful Links

    Project Slack               : https://lyit.slack.com/archives/C023TM3FRS6
    GitHub                      : https://github.com/rlennon/chickchickrun21
    Azure DevOps Project        : https://dev.azure.com/miguelpinar/ChickChickRun21
    Wiki Page                   : https://dev.azure.com/miguelpinar/ChickChickRun21/_wiki/wikis/ChickChickRun21.wiki/5/ChickChickRun21
    
    
    
For more information visit our other sections
<pick from the sample sections below and add your own>
Section 	Description
Process 	Describes the companies process
Project Log 	Log of project activities
Costings 	Overview of the project cost
Architecture 	Outlines the architecture
Environments 	Overview of the environment set-up
DR Plan 	Disaster Recovery Plan and procedures
Requirements 	Overview of the requirements for the project
SLAs 	Service level agreements
Risk Management 	How we manage risk
Security 	Overview of security
Project Log 	Team log for the project

  
## Risk Register

These are the current Risks on the project, re-aligned on a weekly basis

    Team is finding itself to be running short on time due to other work and study commitments
    No PO feedback on software
    Unknown technology choices has led to a lot of upskilling required
    Changing / ambiguous requirements
    Lack of rights for toolsets chosen has hindered progress and ability to deliver

## Tenants of Design
<pick from the sample sections below and add your own>
    Dedication to clean, secure, performant and self documented code
        code Frameworks used
        code coverage tool used
        Secure code: Regex for cleansing and validation, Named queries and database triggers
        performance testing tool to be used : k6.io (https://k6.io/)
    Documentation / code commenting /seperate branch
    Datastore for persistance : Azure Storage Account (Versioning enabled)
    Testing:
        Unit testing : JUnit (Java) / TFLINT (Terraform) 
        integretation testing : pester 
        UA : k6
        security : sonarqube
    Environments:
        staging and production
        tight configuration management for consistency and reproducibility
        automated creation and deployments
        integrated and automated pipeline (commit -> test -> deploy)
    Github version control:
        branches used:
                main
                Task1 
                Task2
                Task3
                Task4
                Task5
                Task6
                Task7
        version/release management
    Agile project management methods/principles (Azure DevOps)

## Social Contract

### Meetings

    Stand-ups will occur on Mondays, 7.30 PM UTC (automated) and Wednesdays, 7 PM UTC (face-to-face).
    The order that people give their updates will be based on alphabetical order of those present at the meeting.
    Updates will be in the form: What I've done, Implediments, What I plan to do
    Sprint planning will occur every <Friday 8:30 PM Irish Time>.
    Please add and update items within <open discussion> prior to the sprint planning session.
    Sprint retro will occur <Friday 8:00 PM Irish Time>.
    The order that people present their sprint retro updates will be based on sample: reverse alphabetical order of those present at the meeting.
    Points raised in the sprint retro will be noted and posted on the slack channel by the Scrum Master.
    Backlog refinement? BlackBoard, Fridays 7.30 PM Irish time, Scrum Master to review backlog items
    Task estimation will be done using Poker Planner. 
    Come prepared to meetings.
    Be on time for Stand Ups and meetings.
    Mobile phones on silent.
    Everyone has equal voice and valuable contribution.
    Keep your language and tone professional at all times.
    Be honest.

### Communication

    Slack is the preferred method of communication.
    If a demonstration is required use Teams, record the session and upload to the Slack channel.
    Slack communications between "7 PM - 9 PM Irish Time".
    Raise a problem as soon as you see it.
    Respect each other and understand differences in knowledge.
    All team documents are to be created using Markdown language and shared on GitHub.
    There are no silly questions, if you don’t understand, ask.
    Share success stories.
    Focus on the positives.
    Don’t make assumptions.
    Don’t interrupt and cut another person off while they are talking.
    Listen when someone is talking, don’t interject.
    Zero tolerance for bullying.
    Agile way of working.
    If are assigned a job, take ownership of it and keep it up to date.
    Stick to your agreed working patterns. Let the team know when you are late or going early.
    Keep Azure DevOps board updated at all times.
    Update the Scrum Board as you progress the story i.e. don’t update at standup.
    Don't be afraid to ask for help.
    Don't be afraid to give constructive critism, as long as it is constructive.
    Solve roadblocks within the team. If the impediment can’t be solved within the team then give it to the Scrum Master.

## Other

    Sprints will start <18th June 2021, 8 PM & Irish time>.
    The Scrum Master role rotates each week, the schedule is available on the on the process section
    Jira will be used for task management and planning.
    Each member of the team will work 7h per week, unless they are on vacation.


### Scrum Master 
    Wk 1  Akhilesh Sasimohan
    Wk 2  Eyituoyo George Ikomi
    Wk 3  Maria Pavlova
    Wk 4  Miguel Pinar
    Wk 5  Jomy Jose
    Wk 6  Jorge Racionero
    Wk 7  Juarez Alvares Barbosa Junior
           
### Branching Strategy
One branch per task & per team member 
Pull & Rebase before triggering a Pull Request 
Two people to review & approve each Pull Request

Branching Strategy to be used is Git flow and describes the following:

1. Master/Main branch - This will only contain finished work. All releases
will be taking place from this branch.

2. Develop Branch - Work on any new or subsequent release occurs here, after
which it is merged to the Master/Main branch once completed.

3. Separate Feature branches to be used for all new features. To be used to
keep track of ongoing development regarding a particular feature. On 
completion, it will be merged to the Develop branch.

4. In a situation where a bug with catastrophic consequencies/implications is
discovered in production, a "hotfix" branch for the purpose of fixing the bug
in question is created. On completion, this will then be merged to the Master/
Main branch, and a new release for production is made. 


### Estimating Story Points

The teams team's velocity is calculated by using Poker Planner

The teams current story point velocity is "<Choose the number!>"
