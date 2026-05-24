````md
# Katalon Automation Testing

![Katalon](https://img.shields.io/badge/Katalon_Studio-10.x-00c7b7?style=for-the-badge&logo=katalon&logoColor=white)
![Groovy](https://img.shields.io/badge/Groovy-4.x-4298B8?style=for-the-badge&logo=apachegroovy&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.x-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![CI](https://img.shields.io/badge/CI-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

End-to-end automation testing framework built with **Katalon Studio**, **Groovy**, and **Selenium WebDriver**, implementing the **Page Object Model (POM)** design pattern for maintainable and scalable test suites.

---

# Table of Contents

- [About](#about)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Test Report](#test-report)
- [Test Suites](#test-suites)

---

# About

This repository contains automated end-to-end testing for the DemoBlaze e-commerce application using Katalon Studio.

The framework follows automation testing best practices including:
- Page Object Model (POM)
- Reusable Keywords
- Modular Test Cases
- Screenshot Reporting
- HTML/PDF Reports
- Organized Test Suites

**Target Application:**  
https://www.demoblaze.com

---

# Test Coverage

## Authentication
- ✅ Login with valid credentials
- ✅ Login with invalid password
- ✅ Empty username/password validation
- ✅ Logout functionality

## Signup
- ✅ Register new user
- ✅ Duplicate username validation
- ✅ Empty field validation

## Product Catalog
- ✅ Product visibility
- ✅ Category filtering
- ✅ Product detail page
- ✅ Product price validation
- ✅ Add to cart

## Cart & Checkout
- ✅ Product added to cart
- ✅ Cart total validation
- ✅ Delete cart item
- ✅ Complete checkout flow

---

# Tech Stack

| Tool | Purpose |
|---|---|
| [Katalon Studio](https://katalon.com/) | Automation testing framework |
| [Groovy](https://groovy-lang.org/) | Programming language |
| [Selenium WebDriver](https://www.selenium.dev/) | Browser automation |

---

# Project Structure

```text
katalon-demoblaze-automation/
├── Test Cases/
│   ├── TC_Login
│   ├── TC_Product
│   ├── TC_Cart
│   └── TC_Signup
│
├── Test Suites/
│   ├── Smoke_Test
│   ├── Regression_Test
│   └── Sanity_Test
│
├── Keywords/
│   └── pages/
│       ├── LoginPage.groovy
│       ├── SignupPage.groovy
│       ├── ProductPage.groovy
│       ├── CartPage.groovy
│       └── CommonKeywords.groovy
│
├── Reports/
├── Object Repository/
├── Profiles/
├── .gitignore
└── README.md
```

---

# Getting Started

## Prerequisites

Before running this project, make sure you have:

- Katalon Studio installed
- Google Chrome installed
- ChromeDriver compatible with your browser version

---

# Installation

```bash
# Clone repository
git clone https://github.com/hast99/katalon-demoblaze-automation.git

# Open project in Katalon Studio
File → Open Project
```

---

# Running Tests

## Run Individual Test Case

```text
Test Cases
→ Right Click Test Case
→ Run
```

---

## Run Test Suite

```text
Test Suites
→ Right Click Test Suite
→ Run
```

Example:
- Smoke_Test
- Regression_Test
- Sanity_Test

---

# Test Suites

| Test Suite | Description |
|---|---|
| Smoke_Test | Quick validation of core features |
| Regression_Test | Full end-to-end regression testing |
| Sanity_Test | Validation after bug fixes or minor changes |

---

# Test Report

Katalon automatically generates execution reports after test execution.

Generated reports include:
- ✅ HTML Report
- ✅ PDF Report
- ✅ CSV Report
- ✅ Execution Logs
- ✅ Screenshots

Report location:

```text
Reports/
```

Example:

```text
Reports/
└── Regression_Test/
    └── 20260524_220000/
         ├── report.html
         ├── report.pdf
         ├── report.csv
         └── screenshots/
```

---

# Screenshot Reporting

The framework automatically captures screenshots during execution for evidence and debugging purposes.

Example:

```groovy
common.takeScreenshot('TC_Login_PASS')
```

# Author

**Hafidh Syahputra**  
QA Engineer

GitHub:  
https://github.com/hast99
````
