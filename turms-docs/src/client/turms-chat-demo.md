# Turms Chat Demo

## Background

Initially, our plan was to let users to reuse existing XMPP clients by making turms-gateway support the XMPP protocol. However, both paid and free XMPP clients have generally low quality, mainly due to the following reasons:

1. Most XMPP client projects have poor code quality, especially early client engineers who lack coding skills. They often mix complex UI logic with business logic (e.g., the famous open-source project JMeter), making it difficult for redevelopment. It is better to rewrite them from scratch.
2. Both commercial and open-source XMPP clients have UI designs that are at an amateur level. If a client project lacks a professional UI, we doubt the capabilities of their frontend engineers and UI designers (a competent intermediate frontend engineer should be capable of designing a single product UI independently). We do not recommend users to adopt their solutions.
3. There is hardly any open-source XMPP client that supports a complete cross-platform solution.
4. Many low-quality XMPP clients even require payment.

Considering that developing a cross-platform IM application is not difficult and mainly involves manual work, and that IM application UI and functionalities are highly generic (researching 10 commercial IM applications in the market would reveal that at least 9 of them have similar UI and functionalities), we decided to first provide the IM client demo `turms-chat-demo-flutter` for Turms users to use or redevelopment. We will support the XMPP protocol later.

## Roadmap

- November-December 2023: Complete desktop UI design; set up Flutter project framework; develop and test basic desktop components; complete Windows UI development and testing.
- December 2023-January 2024: Adapt the UI for MacOS; develop and test basic mobile components; complete Android UI development and testing.
- January-February 2024: Adapt the UI for iOS.
- February-March 2024: Develop the UI for the web.
- March-April 2024: Integrate turms-client-dart and implement IM business logic (the above tasks only involve UI development and testing, excluding business logic).

Note:

- Considering other tasks, holidays, and work situations at Turms, the above timeline may be subject to slight changes.
- There is no plan to support mini programs.

## Introduction

We want to emphasize the term `demo` in the project name. This term mainly has the following meanings:

1. Whether from a product perspective or a technical perspective, this client "demo" is just one of the "possible" solutions. Users should not limit their ability to design their own IM products because of this "demo." Especially, do not assume that Turms' server is customized for this "demo." As repeatedly mentioned in the Turms documentation, Turms is a generic IM solution dedicated to solving various IM scenarios.
2. Prepare for users' further development. This mainly involves three aspects:
   1. Separation of UI and business logic. This allows teams that need to do redevelopment to reuse the UI to implement their own business logic. Readers can even use the `turms-chat-demo-flutter` project without the Turms server, but instead use their own self-developed IM server.
   2. We continue to use the permissive Apache 2.0 license instead of the more restrictive GPL license commonly used in client open-source projects.
   3. Since the UI design of IM applications worldwide is very similar, this `demo` will also implement most of the generic UI and logic for IM. It generally does not provide more customized logic to facilitate redevelopment by other teams.

Note: `demo` does not imply "low quality." Readers will understand this by examining the code quality and UI design later.

## Redevelopment

Due to the numerous design patterns for Flutter applications, many applications lack a unified design, resulting in multiple conflicting designs within a single application, making the architecture look very chaotic.

In order to unify the architecture and code design of this application, making it easier for readers to read the code and engineers to add code, this chapter explains the project's state management and architecture.

### State Management

There are many state management solutions for Flutter, with at least dozens of them. For application-level state management, turms-chat-demo-flutter adopts the mainstream, Flutter officially recommended, more in line with Flutter's own design, and actively updated solution, which is Riverpod.

Although there are other state management solutions for Flutter, either they introduce unnecessary complexity (such as Bloc), or they are too invasive (such as GetX), or they have significant differences from Flutter's native style, or they are not updated for a long time, or they are more experimental. Therefore, they are not adopted.

In addition, besides using Riverpod to implement state management, this application also uses it to implement dependency injection.

### Architecture

Not only are there many design patterns for Flutter application architecture, but there are also multiple ways to practice the same architecture design. Based on the design tradition of Flutter applications, this project chooses the most suitable architecture design pattern for its own situation:

For application-level architecture design: based on Riverpod, adopting a hybrid architecture design of MVC+S and MVVM.

- Model => Repository: Responsible for interacting with external data source interfaces for CRUD operations.
- View => Widget: Responsible for UI presentation.
- Controller + View Model => Controller: Responsible for receiving user input and performing business logic based on services; manages the business state of business components for UI presentation.
- Service: Responsible for executing business logic, connected to controller above and repository below. It is not called a common `domain` because `domain` is a vague term that can refer to not only service but also repository, and even both controller, service, and repository at the same time, representing the "business domain".

Note:

- The controller mentioned in this chapter is the controller in the application architecture layers, not the controller of Flutter widgets, such as AnimationController.

- In some Flutter projects, the controller is not only a controller but also a view model. In this application, the controller is just a controller, but it also includes view models, which are states.

- Complex projects may adopt a 5-layer architecture, namely: View, Controller, Service, Repository, Data Source. However, this application has relatively simple logic, so it only adopts a 3-layer or 4-layer architecture, namely: View, Controller, Service (optional), Repository.

- If readers read open-source desktop projects with a history of more than 10 years, you may often find that the `model` class of such projects may contain more complex business logic.

  This is because in early desktop development and object-oriented design, `model` is a more comprehensive concept, often referring to both the more common `model/entity` (data model, which does not include data processing logic or only includes basic data processing logic) and `repository` (the repository layer for obtaining, processing, and responding with data). However, because such a design obviously does not conform to the design principle of Separation of Concerns, reliable modern projects will not adopt such a design.

### Directory Structure

Based on the above architecture design, the directory structure of this project is roughly as follows:

- ui
  - components: Shared UI widgets such as buttons, tabs, etc.
  - screens: Application pages. Each page includes not only Widgets but also their respective controllers.
  - themes: Themes.
- domain
  - user
    - services
    - repositories
  - message
    - services
    - repositories
  - ...
- infra:
  - preferences: Manage local application configurations.
  - routes: Routes.
  - window: Manage desktop windows.
  - ...