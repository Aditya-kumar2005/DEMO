import os
import pyttsx3
from pocketsphinx import LiveSpeech
from commands.open_app import open_application
from commands.play_music import play_music

engine = pyttsx3.init()
def speak(text):
    engine.say(text)
    engine.runAndWait()

def listen():
    speech = LiveSpeech(lm=False, keyphrase='assistant', kws_threshold=1e-20)
    for phrase in speech:
        print("Wake word detected. Listening for command...")
        speak("Yes?")
        command = LiveSpeech().next()
        handle_command(str(command).lower())

def handle_command(command):
    if "open" in command:
        open_application(command)
    elif "play music" in command or "play song" in command:
        play_music()
    else:
        speak("Sorry, I didn't understand that.")

if __name__ == "__main__":
    speak("Assistant is ready.")
    listen()