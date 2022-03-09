# Python
from datetime import (
    date,
    datetime
)
from typing import Any, Callable, Iterable, TypeVar
from uuid import UUID
import json

# Pydantic
from pydantic import (
    BaseModel,
    EmailStr,
    Field
)

# FastAPI
from fastapi import (
    Body,
    FastAPI,
    HTTPException,
    Path,
    status
)

app = FastAPI()


# Models

# - Users
class BaseUser(BaseModel):
    user_id: UUID = Field(...)
    email: EmailStr = Field(...)


class UserLogin(BaseUser):
    password: str = Field(
        default=...,
        min_length=8,
        max_length=64
    )


class User(BaseUser):
    first_name: str = Field(
        default=...,
        min_length=1,
        max_length=50
    )
    last_name: str = Field(
        default=...,
        min_length=1,
        max_length=50
    )
    birth_date: date | None = Field(None)


class UserRegister(User, UserLogin):
    pass


# - Tweets
class Tweet(BaseModel):
    tweet_id: UUID = Field(...)
    content: str = Field(
        default=...,
        min_length=1,
        max_length=256
    )
    created_at: datetime = Field(datetime.now())
    updated_at: datetime | None = Field(None)
    by: BaseUser = Field(...)


# Utils
_T = TypeVar("_T")


def find(search: Callable[[_T], bool], iterable: Iterable[_T]):
    list_filter = filter(search, iterable)

    try:
        return next(list_filter)
    except StopIteration:
        return None


def findIndex(search: Callable[[int, _T], bool], iterable: Iterable[_T]):
    index_found = -1
    for (i, value) in enumerate(iterable):
        if search(i, value):
            index_found = i
            break

    return index_found


# Path Operations

# - Users
@app.post(
    path="/signup",
    response_model=User,
    status_code=status.HTTP_201_CREATED,
    summary="Register a User",
    tags=["Users"],
    responses={
        409: {
            "content": {
                "application/json": {
                    "example": {"detail": "Conflit data"}
                }
            }
        }
    }
)
def signup(user: UserRegister = Body(...)):
    """
    Signup

    This path operation register a user in the app

    Parameters:
    - Request body parameter:
        - **user: UserRegister**

    Returns a json with the basic user information: **User**
    """

    with open("./users.json", "r+", encoding="utf-8") as f:
        results = json.loads(f.read())
        user_id_str = str(user.user_id)

        index_found = findIndex(
            lambda _, user: user["user_id"] == user_id_str, results)
        if index_found > -1:
            raise HTTPException(
                status_code=status.HTTP_409_CONFLICT, detail="¡This user already exists!")

        user_dict = user.dict()
        user_dict["user_id"] = user_id_str
        user_dict["birth_date"] = str(user_dict["birth_date"])
        results.append(user_dict)
        f.seek(0)
        f.write(json.dumps(results, indent=2))
        return user


@app.post(
    path="/login",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Login a User",
    tags=["Users"]
)
def login():
    # TODO: Falta implementar
    pass


@app.get(
    path="/users",
    response_model=list[User],
    status_code=status.HTTP_200_OK,
    summary="Show all Users",
    tags=["Users"]
)
def show_users():
    """
    Show all Users

    This path operation shows all users in the app

    Returns a json list with the basic users information: **list[User]**
    """

    with open("./users.json", "r", encoding="utf-8") as f:
        results = json.loads(f.read())
        return results


@app.get(
    path="/users/{user_id}",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Show a User",
    tags=["Users"],
    responses={
        404: {
            "content": {
                "application/json": {
                    "example": {"detail": "Resource not found"}
                }
            }
        }
    }
)
def show_user(user_id: UUID = Path(...)):
    """
    Show User by ID

    This path operation show one user by ID in the app

    Parameters:
    - Path parameters:
        - **user_id: UUID**

    Returns a json object with the basic user information
    """

    with open("./users.json", "r", encoding="utf-8") as f:
        results = json.loads(f.read())
        user_id_str = str(user_id)
        user = find(lambda user: user["user_id"] == user_id_str, results)
        if not user:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND, detail="¡This user doesn't exists!")

        return user


@app.put(
    path="/users/{user_id}",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Update a User",
    tags=["Users"],
    responses={
        404: {
            "content": {
                "application/json": {
                    "example": {"detail": "Resource not found"}
                }
            }
        }
    }
)
def update_user(user_id: UUID = Path(...), user: UserRegister = Body(...)):
    """
    Update User

    This path operation update one user in the app

    Parameters:
    - Path parameters:
        - **user_id: UUID**
    - Request body parameter:
        - **user: UserRegister**

    Returns a json object with the basic user information
    """

    with open("./users.json", "r+", encoding="utf-8") as f:
        results: list = json.loads(f.read())
        user_id_str = str(user_id)
        index_found = findIndex(
            lambda _, user: user["user_id"] == user_id_str, results)

        if index_found == -1:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND, detail="¡This user doesn't exists!")

        user_dict = user.dict()
        user_dict["user_id"] = user_id_str
        user_dict["birth_date"] = str(user_dict["birth_date"])
        results[index_found] = user_dict
        f.seek(0)
        f.write(json.dumps(results, indent=2))
        f.truncate()
        return user


@app.delete(
    path="/users/{user_id}",
    response_model=User,
    status_code=status.HTTP_200_OK,
    summary="Delete a User",
    tags=["Users"],
    responses={
        404: {
            "content": {
                "application/json": {
                    "example": {"detail": "Resource not found"}
                }
            }
        }
    }
)
def delete_user(user_id: UUID = Path(...)):
    """
    Delete User by ID

    This path operation delete one user by ID in the app

    Parameters:
    - Path parameters:
        - **user_id: UUID**

    Returns a json object with the basic user information
    """

    with open("./users.json", "r+", encoding="utf-8") as f:
        results: list = json.loads(f.read())
        user_id_str = str(user_id)
        index_found = findIndex(
            lambda _, user: user["user_id"] == user_id_str, results)

        if index_found == -1:
            raise HTTPException(
                status_code=status.HTTP_404_NOT_FOUND, detail="¡This user doesn't exists!")

        user = results[index_found]
        del results[index_found]
        f.seek(0)
        f.write(json.dumps(results, indent=2))
        f.truncate()
        return user


# - Tweets
@app.get(
    path="/",
    response_model=list[Tweet],
    status_code=status.HTTP_200_OK,
    summary="Show all Tweets",
    tags=["Tweets"]
)
def home():
    """
    Show all Tweets

    This path operation shows all tweets in the app

    Returns a json list with the basic tweets information: **list[Tweet]**
    """

    with open("./tweets.json", "r", encoding="utf-8") as f:
        results = json.loads(f.read())
        return results


@app.post(
    path="/post",
    response_model=Tweet,
    status_code=status.HTTP_201_CREATED,
    summary="Post a Tweet",
    tags=["Tweets"]
)
def post_tweet(tweet: Tweet = Body(...)):
    """
    Post a Tweet

    This path operation post a tweet in the app

    Parameters:
    - Request Body parameter:
        - **tweet: Tweet**

    Returns a json with the basic tweet information: **Tweet**
    """

    with open("./tweets.json", "r+", encoding="utf-8") as f:
        results = json.loads(f.read())
        tweet_dict = tweet.dict()
        tweet_dict["tweet_id"] = str(tweet_dict["tweet_id"])
        tweet_dict["created_at"] = str(tweet_dict["created_at"])

        if tweet_dict["updated_at"]:
            tweet_dict["updated_at"] = str(tweet_dict["updated_at"])

        tweet_dict["by"]["user_id"] = str(tweet_dict["by"]["user_id"])

        results.append(tweet_dict)
        f.seek(0)
        f.write(json.dumps(results, indent=2))
        return tweet


@app.get(
    path="/tweets/{tweet_id}",
    response_model=Tweet,
    status_code=status.HTTP_200_OK,
    summary="Show a Tweet",
    tags=["Tweets"]
)
def show_tweet():
    # TODO: Falta implementar
    pass


@app.put(
    path="/tweets/{tweet_id}",
    response_model=Tweet,
    status_code=status.HTTP_200_OK,
    summary="Update a Tweet",
    tags=["Tweets"]
)
def update_tweet():
    pass


@app.delete(
    path="/tweets/{tweet_id}",
    response_model=Tweet,
    status_code=status.HTTP_200_OK,
    summary="Delete a Tweet",
    tags=["Tweets"]
)
def delete_tweet():
    pass
