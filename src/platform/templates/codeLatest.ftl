<html lang="en">
    <header>
        <title>Latest</title>
    </header>
    <style>
        body {
            display: flex;
            flex-direction: column;
        }
        #code_snippet {
            background: #f4f4f4;
            border: 1px solid #ddd;
            border-left: 3px solid #f36d33;
            color: #666;
            page-break-inside: avoid;
            font-family: monospace;
            font-size: 15px;
            line-height: 1.6;
            margin-top: 1.5px;
            margin-bottom: 1.6em;
            max-width: 100%;
            overflow: auto;
            padding: 1em 1.5em;
            display: block;
            word-wrap: break-word;
        }
        #load_date {
            font-family: Arial, sans, serif;
            font-size: 9pt;
            font-weight: bold;
            color: blue;
        }
    </style>
    <body>
        <#list codeList as code>
            <span id="load_date"> ${code.date}  </span>
            <pre id="code_snippet"> ${code.code} </pre>
        <#else>
            <span id="load_date"> ${date}  </span>
            <pre id="code_snippet"> ${code} </pre>
        </#list>
    </body>
</html>