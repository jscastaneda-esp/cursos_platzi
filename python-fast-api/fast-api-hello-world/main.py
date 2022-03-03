# Pydantic
from pydantic import BaseModel, Field

# FastAPI
from fastapi import FastAPI, Query

app = FastAPI()


# Models
class Person(BaseModel):
    first_name: str = Field(..., min_length=1, max_length=50)
    last_name: str = Field(..., min_length=1, max_length=150)
    age: int = Field(..., gt=0, le=200)
    # In 3.6 < Python < 3.10, hair_color: typing.Optional[str] = None
    hair_color: str | None = Field(None, regex="[black|blonde]")
    # In 3.6 < Python < 3.10, is_married: typing.Optional[bool] = None
    is_married: bool | None = None


@app.get("/")
def home():
    return {"Hello": "World"}


# Request and Response Body
@app.post("/person/new")
def create_person(person: Person):
    return person


# Validations: Query parameters
@app.get("/person/detail")
def show_person(
    name: str | None = Query(None, min_length=1, max_length=50), age: int = Query(...)
):
    return {name, age}
