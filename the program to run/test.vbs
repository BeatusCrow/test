Set WshShell = WScript.CreateObject("WScript.Shell")
CurrentPath = WshShell.CurrentDirectory
ModulPath = CurrentPath & "\javafx-sdk-17.0.9\lib"
JrePath = CurrentPath & "\java\bin\java.exe"
JarPath = CurrentPath & "\other\test.jar"
a = " --add-modules javafx.controls,javafx.fxml -Dfile.encoding=UTF-8 -jar " 
CommandStr = JrePath & " --module-path " & ModulPath & a & JarPath
WshShell.Run CommandStr, 0, True