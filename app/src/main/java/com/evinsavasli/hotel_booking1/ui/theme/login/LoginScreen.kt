package com.evinsavasli.hotel_booking1.ui.theme.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evinsavasli.hotel_booking1.R
import com.evinsavasli.hotel_booking1.ui.theme.Hotel_Booking1Theme
import com.evinsavasli.hotel_booking1.ui.theme.components.HeaderText
import com.evinsavasli.hotel_booking1.ui.theme.components.LoginTextField

val defaultPadding=16.dp
val itemSpacing=8.dp

@Composable
fun LoginScreen(onLoginClick:()->Unit,onSignUpClick:()->Unit){
    val (userName,setUserName)= rememberSaveable {
        mutableStateOf("")
    }
    val (password,setPassword)= rememberSaveable {
        mutableStateOf(value = "")
    }
    val(checked,onCheckedChange)= rememberSaveable {
        mutableStateOf(false)
    }
    val isFieldsEmpty = userName.isNotEmpty() && password.isNotEmpty()
    val context = LocalContext.current



    Column (
        modifier= Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )
        LoginTextField(
            value = userName,
            onValueChange = setUserName,
            labelText = "Username",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(itemSpacing))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Text(text = "Remember me")
            }
            TextButton(onClick = { }) {
                Text("Forgot Password?")

            }
        }
        Spacer(Modifier.height(itemSpacing))
        Button(onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsEmpty) {
            Text("Login")

        }
        AlternativeLoginOptions(
            onIconClick ={index->
                         when(index){
                             0->{
                                 Toast.makeText(context,"Instagram Login Click",Toast.LENGTH_SHORT).show()
                             }
                             1->{
                                 Toast.makeText(context,"Google Login Click",Toast.LENGTH_SHORT).show()
                             }



                         }
            },
            onSignUpClick = onSignUpClick,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)

            )

    }

}

@Composable
fun AlternativeLoginOptions(
    onIconClick:(index:Int)->Unit,
    onSignUpClick:()->Unit,
    modifier: Modifier=Modifier){
    val iconList = listOf(
        R.drawable.instagram,
        R.drawable._440809

    )


    Column(
        modifier=modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        Text("Or Sign in With")
       Row (horizontalArrangement = Arrangement.SpaceEvenly){

       iconList.forEachIndexed{index,iconResId->


        Icon(
            painter=painterResource(iconResId),
            contentDescription = "alternative Login",
            modifier= Modifier
                .size(32.dp)
                .clickable {
                    onIconClick(index)
                }
                .clip(CircleShape)
            )

        Spacer(Modifier.width(defaultPadding) )
        }
          Spacer(Modifier.width(defaultPadding))
       }
        Spacer(Modifier.height(defaultPadding))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Don't have an account?")
            Spacer(Modifier.height(itemSpacing))
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up")

            }


        }
    }


}
@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen () {
    Hotel_Booking1Theme {
        LoginScreen({},{})

    }

}