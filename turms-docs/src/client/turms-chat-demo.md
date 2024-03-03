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

There are many state management solutions for Flutter, with at least dozens of them. For application-level state management, turms-chat-demo-flutter adopts the mainstream, Flutter officially recommended, more in line with Flutter's own design, and actively updated library, which is Riverpod.

Although there are other state management solutions for Flutter, either they introduce unnecessary complexity (such as Bloc), or they are too invasive (such as GetX), or they have significant differences from Flutter's native style, or they are not updated for a long time, or they are more experimental. Therefore, they are not adopted.

In addition, besides using Riverpod to implement state management, this application also uses it to implement dependency injection.

### Architecture

Not only are there many design patterns for Flutter application architecture, but there are also multiple ways to practice the same architecture design. Based on the design tradition of Flutter applications, this project chooses the most suitable architecture design pattern for its own situation:

For application-level architecture design: based on Riverpod, adopting a hybrid architecture design of MVC+S and MVVM.

- Model => Repository: Responsible for interacting with external data source interfaces for CRUD operations.
- View => Widget: Responsible for UI presentation.
- Controller + View Model:
  - Controller: Responsible for receiving user input and executing business logic based on services; manages the business state of business components for display on the UI layer.
  - View Model: Responsible for storing various states and notifying listeners (observer pattern) when the state changes.

- Service: Responsible for executing business logic, connected to controller above and repository below. It is not called a common `domain` because `domain` is a vague term that can refer to not only service but also repository, and even both controller, service, and repository at the same time, representing the "business domain".

Note:

- The controller mentioned in this chapter is the controller in the application architecture layers, not the controller of Flutter widgets, such as AnimationController.

- In some Flutter projects, the controller is not only a controller but also a view model. In this application, the controller is just a controller, but it also includes view models, which are states.

- Complex projects may adopt a 5-layer architecture, namely: View, Controller, Service, Repository, Data Source. However, this application has relatively simple logic, so it only adopts a 3-layer or 4-layer architecture, namely: View, Controller, Service (optional), Repository.

- If readers read open-source desktop projects with a history of more than 10 years, you may often find that the `model` class of such projects may contain more complex business logic.

  This is because in early desktop development and object-oriented design, the `Model` was a more comprehensive concept, often referring to what is now more commonly known as `Model/Entity` (data model, which does not contain data processing logic, or only contains basic data processing logic) and `Repository` (the storage layer for obtaining, processing, and responding to data).
  
  On one hand, such a design clearly does not conform to the design principle of Separation of Concerns.
  
  On the other hand, we often say that class design should be highly cohesive and loosely coupled. However, if we take classes as points and the class hierarchy as a plane (one manifestation is the directory structure), mixing data models with repository layer logic can also lead to low cohesion and high coupling in code layers. This is an anti-paradigm design caused by ignorance, rather than a consciously anti-paradigm design.
  
  Therefore, reliable modern projects no longer adopt such designs.
  
  - Some Flutter projects' Controllers are not just Controllers, but also (are) View Models. But their disadvantages are as mentioned above.
  
  So in this application, the Controller is just a Controller, and it can contain (has) zero to multiple View Models, i.e., States.
  
  - Many excellent design concepts are interchangeable, only the specific terms may differ. In the field of web applications, when we design UI components, we usually distinguish between Smart Components (those that contain logic, especially business logic) and Dumb Components (those that do not contain logic, or only contain very simple logic). The `View` mentioned in turms-chat-demo is a Dumb Component, and the combination of `View` and `Controller` constitutes Smart Components.

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

UI components have a many-to-many relationship with the domain, which is why the turms-chat-demo does not place UI components and business domains in the same level directory like many other applications do.

### IPC

The IPC in turms-chat-demo is implemented using the WebSocket protocol and JSON-RPC 2.0 transfer format for features such as singleton application, automatic updates, and communication with third-party applications.

The reasons for not using Unix Domain Sockets are:

- Windows platform itself has many bugs related to Unix Sockets.

  For example:

  - https://github.com/golang/go/issues/33357
  - As of March 2024, although Windows' official documentation claims support for Abstract Namespace, it does not actually support it (related bug issue: https://github.com/microsoft/WSL/issues/4240).

- To reduce unnecessary development and maintenance costs. Since both MacOS and Windows do not support the Abstract Namespace feature, it is necessary to rely on the file system:

  - Unix Socket Domain requires that the file path length character count must not exceed 108 characters (null-terminated string). To ensure the robustness of the program, various fallbacks are naturally required, leading to cumbersome code.
  - If turms-chat-demo is not properly closed (e.g., the user's computer crashes), the Unix Domain Socket in the file system will not be automatically deleted. Therefore, it is necessary to check whether the Unix Domain Socket corresponding file is still valid, and different implementations are required for different platforms. For example, for Linux and MacOS platforms, unlink the file before the server Socket bind. For the Windows platform, create a temporary file first, and during program execution, lock it. If the Unix Domain Socket file exists and the temporary file is also locked, it indicates a valid Socket; otherwise, it is invalid. Of course, this is just a rough implementation idea, and due to different platform implementation details and varying bugs in different versions of the Windows platform, extensive testing and adaptation are still needed.

- Poor scalability. For specific reasons, see below.

The reasons for using WebSocket + JSON-RPC 2.0 are:

- Dart officially provides the ready-made `json_rpc_2` library, which supports WebSocket + JSON-RPC 2.0, so adopting this solution has almost no maintenance cost for us.
- Although turms-chat-demo itself needs to use IPC operations, the usage frequency is extremely low, so there is no need to pursue extreme performance like the Turms servers.
- It is convenient for third-party applications to call turms-chat-demo based on WebSocket + JSON-RPC 2.0.
- There are a large number of client tools on the Internet that support the WebSocket protocol and JSON format, and it is easy to debug the IPC features of turms-chat-demo based on these tools.

### Text Editing

#### Text Editor Library Ecosystem

- appflowy_editor. appflowy_editor is undoubtedly the most feature-rich and highest-quality text editor in the Flutter community. However, it uses a dual open-source license, one of which is AGPL. Therefore, it is not considered.
- flutter_quill: Ignoring the numerous bugs in this dependency library, it basically meets the main functional requirements of turms-chat-demo. However, the maintainer's programming skills are poor, and the code is a typical example of spaghetti code. The maintainer is not yet qualified, so there is no intention to expand its features, and the project is not considered.
- super_editor: The project has high code quality, but it does not support many basic text editing features (as of March 2024), such as redo/undo and inline images. It is better to use Flutter's built-in `TextField` directly.

In summary, considering that there is no reliable open-source text editor available, Flutter's built-in `TextField` is used.

#### Chat Text Protocol

##### Should Support for a Single Message to Display Multiple Videos and Images or Not

Supporting the display of multiple videos and images in a single message is not user-friendly in terms of UI design and software performance.

On one hand, when displaying a message in the UI, the size of the message must be constant. Otherwise, if the size changes with the loading status of the message, such as during loading or failure, it would be a very bad user experience. To ensure that the size of the message does not change with the message status, the overall size of the message must be confirmed when displaying the message UI.

To confirm the size of the rich text message, either the sender or the server must confirm the size of all thumbnails of images and videos during the message sending process and record this size as part of the message, which is then transmitted to the recipient. However, this approach is inflexible, as any change in thumbnail size would invalidate the previous size records.

Alternatively, the recipient must wait for all thumbnails in a message to be downloaded before determining the size of the message based on the thumbnails and then displaying it in the UI. This approach has two significant drawbacks: first, the recipient must wait for all thumbnails to be downloaded before the client can display the message, increasing the message delay. Second, if some thumbnails in a single message fail to download, the client cannot obtain the complete size of the message. In this case, to still display the message, placeholder text or images must be used. Since the download failed, there should naturally be a way to provide users with the option to re-download the failed thumbnails. However, when re-downloading the failed thumbnails, the overall size of the message will change, leading to changes in the size of the entire message list, which is not an elegant UI design.

Considering the library ecosystem of text editors and the development cost and schedule of a self-developed text editor, rich text is not currently supported.

It is worth mentioning that some chat applications developed by junior engineers may dare to implement various complex UI displays. This is because junior engineers usually only consider the simple functional requirements of their own business area, especially without knowing the existence of the concept of "non-functional requirements." In contrast, senior engineers will comprehensively consider requirements from the perspectives of product demand, front-end UI design, backend architecture design, operational costs, the current system architecture and its evolution direction, and even compliance.

##### Text Protocol

Note: The text editor in turms-chat-demo is a WYSIWYG (What You See Is What You Get) editor, so regardless of how the application's text protocol is designed, it is imperceptible to users.

The design of turms-chat-demo's text transmission protocol is inspired by Markdown. The general design idea is: if a certain style has already been defined by standard Markdown, then reuse Markdown's text protocol format. If a certain style is not defined by standard Markdown, then add custom text protocol formats based on Markdown's design ideas.

The various files in the message, such as images, audio, video, etc., correspond to the URLs of the resources.

turms-chat-demo itself will display the corresponding UI components based on the suffix of the resource URL. Additionally, turms-chat-demo does not judge whether the resource URL belongs to a specific server, as our intention is to display resources from any source. If the reader wishes to parse resources only from a specific server and display non-matching resources as plain text, the source code needs to be modified to determine the source of the resource based on the URL or the SSL certificate of the resource provider.

#### Emoji

turms-chat-demo uses the system's built-in Emoji font, namely:

- On Linux platforms, the `Noto Color Emoji` font is used.
- On Apple platforms, the `Apple Color Emoji` font is used.
- On Windows platforms, the `Segoe UI Emoji` font is used.

The reason for this approach is that, as of March 2024, there is no high-quality, clearly free-for-commercial-use Emoji font available on the entire Internet (Note: All Turms projects, including turms-chat-demo itself, are open-source and free for commercial use).

Therefore, using the built-in Emoji fonts of each system to display Emojis avoids copyright-related issues. The only drawback is that the display effect of the same Emoji character varies across different platforms.

#### Sticky

Implemented based on the developer HTTP interface of the most popular global emoji library [GIPHY](https://giphy.com/).

#### Thumbnails

Since Turms currently does not have a dedicated media server (Media Service), and generating thumbnails on the server also requires a lot of resources and costs, turms-chat-demo adopts a compromise solution. That is, when uploading images or videos in a message, turms-chat-demo first requests upload information from the Turms server. The Turms server will guide turms-chat-demo on how to generate the corresponding thumbnail in the response, and turms-chat-demo will generate the required thumbnail according to the instructions and upload both the generated thumbnail and the original image or video to the corresponding OSS service.