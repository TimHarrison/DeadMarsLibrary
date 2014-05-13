#version 130

in vec4 color;
out vec4 fragColor;

void main()
{
	fragColor = 2 * color;
}