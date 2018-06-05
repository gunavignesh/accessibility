$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("test.feature");
formatter.feature({
  "line": 1,
  "name": "Gather the Accebility report of Website",
  "description": "Verify if user is able to Login in to the site",
  "id": "gather-the-accebility-report-of-website",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Open and Record Accessibility Report",
  "description": "",
  "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "Opens webpage \u003cwpg\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Runs Accessibility Report Tool",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Excel output is records the report",
  "keyword": "Then "
});
formatter.examples({
  "line": 9,
  "name": "",
  "description": "",
  "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;",
  "rows": [
    {
      "cells": [
        "wpg"
      ],
      "line": 10,
      "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;;1"
    },
    {
      "cells": [
        "\"https://www.google.com/\""
      ],
      "line": 11,
      "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;;2"
    },
    {
      "cells": [
        "\"http://www.beckmaneserve.com\""
      ],
      "line": 12,
      "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 11,
  "name": "Open and Record Accessibility Report",
  "description": "",
  "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "Opens webpage \"https://www.google.com/\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Runs Accessibility Report Tool",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Excel output is records the report",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.google.com/",
      "offset": 15
    }
  ],
  "location": "Accessibility.openpage(String)"
});
formatter.result({
  "duration": 6483278464,
  "status": "passed"
});
formatter.match({
  "location": "Accessibility.opentool()"
});
formatter.result({
  "duration": 9637584,
  "status": "passed"
});
formatter.match({
  "location": "Accessibility.excelwrite()"
});
formatter.result({
  "duration": 15234296511,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Open and Record Accessibility Report",
  "description": "",
  "id": "gather-the-accebility-report-of-website;open-and-record-accessibility-report;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "Opens webpage \"http://www.beckmaneserve.com\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Runs Accessibility Report Tool",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Excel output is records the report",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://www.beckmaneserve.com",
      "offset": 15
    }
  ],
  "location": "Accessibility.openpage(String)"
});
formatter.result({
  "duration": 4251605720,
  "status": "passed"
});
formatter.match({
  "location": "Accessibility.opentool()"
});
formatter.result({
  "duration": 9324386,
  "status": "passed"
});
formatter.match({
  "location": "Accessibility.excelwrite()"
});
formatter.result({
  "duration": 10053507608,
  "status": "passed"
});
});