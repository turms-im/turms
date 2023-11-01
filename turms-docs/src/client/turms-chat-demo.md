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
   1. Separation of UI and business logic. This allows teams that require redevelopment to reuse the UI and implement their own business logic.
   2. We continue to use the permissive Apache 2.0 license instead of the more restrictive GPL license commonly used in client open-source projects.
   3. Since the UI design of IM applications worldwide is very similar, this `demo` will also implement most of the generic UI and logic for IM. It generally does not provide more customized logic to facilitate redevelopment by other teams.

Note: `demo` does not imply "low quality." Readers will understand this by examining the code quality and UI design later.