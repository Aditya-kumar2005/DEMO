import os
import pyttsx3

def speak(text):
    engine = pyttsx3.init()
    engine.say(text)
    engine.runAndWait()

def open_application(command):
    if "chrome" in command:
        os.system("start chrome")
        speak("Opening Chrome")
    elif "notepad" in command:
        os.system("start notepad")
        speak("Opening Notepad")
    else:
        speak("Application not recognized.")