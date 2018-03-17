#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

;#RequireAdmin ;Will give your script a permission elevation (sometimes its needed)
Sleep(5000)
Opt("WinTitleMatchMode", 4) ;1=start, 2=subStr, 3=exact, 4=advanced, -1 to -4=Nocase
Opt("WinSearchChildren", 1) ;0=no, 1=search children also
Local $stSystemTime = DllStructCreate('ushort;ushort;ushort;ushort;ushort;ushort;ushort;ushort')
DllCall('kernel32.dll', 'none', 'GetSystemTime', 'ptr', DllStructGetPtr($stSystemTime))
$sMilliSeconds = StringFormat('%03d', DllStructGetData($stSystemTime, 8))
WinWaitActive("Save As","","20")
If Not @error Then
    ControlSetText("Save As","","[CLASS:Edit; INSTANCE:1]","C:\Working\PDFDownloads\Detail.Flash"&$sMilliSeconds&".pdf")
    ControlClick("Save As","","[CLASS:Button; INSTANCE:1]")
 EndIf
