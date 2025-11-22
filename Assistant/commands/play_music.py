from playsound import playsound
import pyttsx3

def speak(text):
    engine = pyttsx3.init()
    engine.say(text)
    engine.runAndWait()

def play_music():
    try:
        playsound("assets/music/song.mp3")
        speak("Playing music")
    except:
        speak("Couldn't play music.")