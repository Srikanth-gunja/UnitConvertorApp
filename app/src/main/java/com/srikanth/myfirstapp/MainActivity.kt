package com.srikanth.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srikanth.myfirstapp.ui.theme.MyFirstAppTheme
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConvertor()
                }
            }
        }
    }
}



@Composable
fun UnitConvertor(){
    var outlineText by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }

    var iUnit by remember { mutableStateOf(false) }
    var oUnit by remember { mutableStateOf(false) }
    val conversionFactor= remember { mutableStateOf(1.00) }
    val oconversionFactor= remember { mutableStateOf(1.00) }
    var output by remember { mutableStateOf(" ") }

    fun conveterorHelper(){
        var inputValue=outlineText.toDoubleOrNull()?:0.0
        var tempresult=(conversionFactor.value.toDouble()*inputValue*100.0);
        var  toutput=(tempresult/oconversionFactor.value.toDouble()).roundToInt()/100.0
        output=toutput.toString()

    }
    Surface() {

         Column(modifier= Modifier
            .fillMaxSize()
            .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =Alignment.CenterHorizontally) {
            Text(text = "Unit Convertor")
            OutlinedTextField(value = outlineText,
                onValueChange ={
                    outlineText=it
                    conveterorHelper() },
                label={Text("Enter Value")}
            )

            Row(modifier = Modifier.padding(10.dp)){
                //input
                Box{
                    Button(onClick = { iUnit=!iUnit },modifier = Modifier.padding(5.dp)) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown," " )
                 }
                    DropdownMenu(expanded = iUnit, onDismissRequest = {  iUnit=! iUnit }) {
                        DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                            inputUnit="centimeters";
                            iUnit=!iUnit;
                            conversionFactor.value=0.01
                            conveterorHelper()
                        })
                        DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                            inputUnit="Meters"
                            iUnit=!iUnit
                            conversionFactor.value=1.00
                            conveterorHelper()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                            inputUnit="Feet"
                            iUnit=!iUnit
                            conversionFactor.value=0.3048
                            conveterorHelper()
                        })
                        DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                            inputUnit="Millimeters"
                            iUnit=!iUnit
                            conversionFactor.value=0.001
                            conveterorHelper()
                        })
                    }
                }
                Spacer(modifier = Modifier.width(30.dp))
                //output
                Box{
                    Button(onClick = { oUnit=!oUnit },modifier = Modifier.padding(5.dp)) {
                        Text(text = outputUnit)
                        Icon( Icons.Default.ArrowDropDown, contentDescription = " ")
                    }

                    DropdownMenu(expanded = oUnit, onDismissRequest = {  oUnit=! oUnit }) {
                        DropdownMenuItem(text = { Text(text = "Centimeters")}, onClick = {
                            outputUnit="centimeters";
                            oUnit=!oUnit;
                            oconversionFactor.value=0.01
                            conveterorHelper()
                        })
                        DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                            outputUnit="Meters";
                            oUnit=!oUnit;
                            oconversionFactor.value=1.00
                            conveterorHelper()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                            outputUnit="Feet";
                            oUnit=!oUnit;
                            oconversionFactor.value=0.3048
                            conveterorHelper() })
                        DropdownMenuItem(text = { Text(text = "Millimeters")}, onClick = {
                            outputUnit="Millimeters";
                            oUnit=!oUnit;
                            oconversionFactor.value=0.001
                            conveterorHelper()
                        })
                    }
                }


            }
            Text(text = "Result is: ${output}")
        }
    }

}


@Composable
@Preview
fun PreviewUnitConvertor(){
    UnitConvertor()
}
